package cl.bootcamp.leeconnos.viewmodel

import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.modelos.Direccion
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.data.repositorio.DireccionRepository
import cl.bootcamp.leeconnos.data.repositorio.DireccionResult
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import cl.bootcamp.leeconnos.utils.obtenerSiglaRegion
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

enum class MetodoEntrega {
    NINGUNO,
    DELIVERY,
    RETIRO_TIENDA
}

enum class MetodoPago {
    NINGUNO,
    TARJETA,
    TRANSFERENCIA
}

data class CheckoutUiState(
    val mostrarDialogoEntrega: Boolean = false,
    val mostrarDialogoTarjeta: Boolean = false,

    val metodoEntrega: MetodoEntrega = MetodoEntrega.NINGUNO,
    val metodoPago: MetodoPago = MetodoPago.NINGUNO,

    val direccionSeleccionada: Direccion? = null,
    val textoUbicacionGps: String = "Usar tu ubicación",
    val textoCupon: String = "",
    val textoBusqueda: String = "",
    val sugerenciasDirecciones: List<AutocompletePrediction> = emptyList(),

    val costoDespacho: Int = 0,
    val descuentoCupon: Int = 0,
    val totalCompra: Int = 0,

    val direccionSugeridaGeocoding: Direccion? = null,
    val errorDireccionNoEncontrada: Boolean = false
){
    val puedePagar: Boolean
        get() = metodoEntrega != MetodoEntrega.NINGUNO && metodoPago != MetodoPago.NINGUNO

    val puedeGuardarDireccion: Boolean
        get() = direccionSugeridaGeocoding != null &&

                direccionSugeridaGeocoding.calle.isNotBlank() &&
                direccionSugeridaGeocoding.comuna.isNotBlank() &&
                direccionSugeridaGeocoding.region.isNotBlank() &&
                direccionSugeridaGeocoding.nombreReceptor.isNotBlank() &&
                direccionSugeridaGeocoding.telefono.isNotBlank() &&

                //Usa el mapa o las sugerencias
                direccionSugeridaGeocoding.latitud != 0.0 &&
                direccionSugeridaGeocoding.longitud != 0.0
}


@OptIn(FlowPreview::class)
class CheckoutViewModel(
    private val libroRepository: LibroRepository,
    private val direccionRepository: DireccionRepository,
    private val placesClient: PlacesClient,
    private val geocoder: Geocoder
) : ViewModel() {

    //Flujo necesario para no llamar a la API por cada cambio del TextField
    private val _textoBusquedaDebounce = MutableStateFlow("")

    val direccionesGuardadas: StateFlow<List<Direccion>> = direccionRepository.direccionesLocales
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        _textoBusquedaDebounce
            .debounce(500.milliseconds)
            .distinctUntilChanged()
            .onEach { busqueda ->
                ejecutarBusquedaEnPlaces(busqueda)
            }
            .launchIn(viewModelScope)

        //Cada vez que se crea el viewmodel llama a sincronizar direcciones
        viewModelScope.launch(Dispatchers.IO) {
            direccionRepository.sincronizarDireccionesDesdeServidor()
        }
    }

    val itemsCarrito: StateFlow<List<Libro>> = libroRepository.carrito
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val subtotalCompra: StateFlow<Int> = itemsCarrito.map { listaLibros ->
        listaLibros.sumOf { libro ->
            libro.precio * libro.cantidad
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = 0
    )

    private val _uiState = MutableStateFlow(CheckoutUiState())
    
    //Une 2 flujos para que cuando haya un cambio en alguno de los dos este reaccione
    val uiState: StateFlow<CheckoutUiState> = combine(_uiState, subtotalCompra) { state, subtotal ->
        
        val montoDescuento = if (state.descuentoCupon > 0) {
            subtotal * (state.descuentoCupon / 100)
        } else {
            0
        }
        
        val totalFinal = subtotal - montoDescuento + state.costoDespacho

        state.copy(
            descuentoCupon = montoDescuento,
            totalCompra = totalFinal
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CheckoutUiState()
    )

    //Dialogos
    fun cambiarMostrarDialogoEntrega(mostrar: Boolean) {
        _uiState.update { it.copy(mostrarDialogoEntrega = mostrar) }
    }

    fun cambiarMostrarDialogoTarjeta(mostrar: Boolean) {
        _uiState.update { it.copy(mostrarDialogoTarjeta = mostrar) }
    }

    //Elección de usuario
    fun seleccionarMetodoEntrega(metodo: MetodoEntrega) {

        _uiState.update { state ->
            state.copy(
                metodoEntrega = metodo,
                mostrarDialogoEntrega = metodo == MetodoEntrega.DELIVERY //Por ahora solo en delivery por falta de wireframe completo
            )
        }
    }

    fun seleccionarMetodoPago(metodo: MetodoPago) {

        _uiState.update { state ->
            state.copy(
                metodoPago = metodo,
                mostrarDialogoTarjeta = metodo == MetodoPago.TARJETA //Por ahora solo tarjeta por falta de wireframe completo
            )
        }
    }

    fun actualizarTextoCupon(texto: String) {
        _uiState.update { it.copy(textoCupon = texto) }
    }

    fun crearYConfirmarDireccion(direccion: Direccion) {

        viewModelScope.launch {

            //Endpoint pide un telefono con formato chileno [+5692345678]
            val telefonoLimpio = direccion.telefono.replace(" ", "").replace("-", "")

            val telefonoFormateado = when {
                //+56912345678
                telefonoLimpio.startsWith("+") -> telefonoLimpio

                //12345678
                telefonoLimpio.length == 8 -> "+569$telefonoLimpio"

                //912345678
                telefonoLimpio.length == 9 -> "+56$telefonoLimpio"

                //56912345678
                telefonoLimpio.length == 11 && telefonoLimpio.startsWith("56") -> "+$telefonoLimpio"


                else -> telefonoLimpio
            }

            val direccionFinal = direccion.copy(telefono = telefonoFormateado)

            when (val result = direccionRepository.crearDireccion(direccionFinal)) {

                is DireccionResult.Success -> {

                    _uiState.update { state ->
                        state.copy(
                            direccionSeleccionada = direccionFinal,
                            metodoEntrega = MetodoEntrega.DELIVERY,
                            mostrarDialogoEntrega = false,
                            costoDespacho = 4000 //TODO: endpoint envíos con datos DUMMY, se atrasa su implementación
                        )
                    }
                }

                is DireccionResult.Error -> {

                    Log.e("CHECKOUT", "Error al crear: ${result.mensaje}")
                }
            }
        }
    }

    fun seleccionarDireccionExistente(direccion: Direccion) {

        _uiState.update { state ->
            state.copy(
                direccionSeleccionada = direccion,
                metodoEntrega = MetodoEntrega.DELIVERY,
                mostrarDialogoEntrega = false,
                costoDespacho = 4000 //TODO: endpoint envíos con datos DUMMY, se atrasa su implementación
            )
        }
    }

    fun cambiarDireccionSugerida(direccion: Direccion) {
        _uiState.update { it.copy(direccionSugeridaGeocoding = direccion) }
    }


    //Con la nueva API de Places hay que generar un token para cada sesión de búsqueda
    private var token = AutocompleteSessionToken.newInstance()

    fun actualizarTextoBusqueda(busqueda: String) {
        _uiState.update { it.copy(textoBusqueda = busqueda) }

        _textoBusquedaDebounce.value = busqueda
    }

    fun seleccionarDireccionDesdePrediccion(prediccion: AutocompletePrediction, onExito: () -> Unit) {

        val placeId = prediccion.placeId

        val campos = listOf(Place.Field.LOCATION, Place.Field.ADDRESS_COMPONENTS)

        val peticion = FetchPlaceRequest.builder(placeId, campos)
            .setSessionToken(token)
            .build()

        placesClient.fetchPlace(peticion)
            .addOnSuccessListener { response ->

                val place = response.place
                val coordenadas = place.location

                var calle = ""
                var numeroCalle = ""
                var comuna = ""
                var ciudad = ""
                var region = ""
                var codigoPostal = ""

                place.addressComponents?.asList()?.forEach { component ->
                    if (component.types.contains("route")) calle = component.name
                    if (component.types.contains("street_number")) numeroCalle = " ${component.name}"

                    if (component.types.contains("locality") || component.types.contains("sublocality")) {
                        comuna = component.name
                    }

                    if (component.types.contains("administrative_area_level_2")) {
                        ciudad = component.name
                    }

                    if (component.types.contains("administrative_area_level_1")) region = component.name
                    if (component.types.contains("postal_code")) codigoPostal = component.name
                }

                //Para proteger de validación en backend
                if (ciudad.isBlank()) {
                    ciudad = comuna
                }

                val calleCompleta = if (numeroCalle.isNotEmpty()) {
                    "$calle$numeroCalle".trim()

                } else {
                    calle
                }

                if (coordenadas != null) {

                    val nuevaDireccion = Direccion(
                        calle = calleCompleta.ifEmpty { prediccion.getPrimaryText(null).toString() },
                        comuna = comuna,
                        ciudad = ciudad,
                        codigoPostal = codigoPostal,
                        region = obtenerSiglaRegion(region),
                        latitud = coordenadas.latitude,
                        longitud = coordenadas.longitude
                    )

                    cambiarDireccionSugerida(nuevaDireccion)
                    cambiarMostrarDialogoEntrega(false)

                    _uiState.update {
                        it.copy(
                            textoBusqueda = "",
                            sugerenciasDirecciones = emptyList()
                        )
                    }

                    _textoBusquedaDebounce.value = ""

                    limpiarTokenDeBusqueda()

                    onExito()
                }
            }

            .addOnFailureListener { exception ->
                Log.e("PLACES_API_ERROR", "Error obteniendo detalles del lugar", exception)
            }
    }

    fun convertirCoordenadasADireccion(location: Location, onExito: () -> Unit) {

        _uiState.update { it.copy(textoUbicacionGps = "Buscando tu ubicación...") }

        viewModelScope.launch(Dispatchers.IO) {

            try {

                val direcciones = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                if (!direcciones.isNullOrEmpty()) {
                    val direccionStr = direcciones[0]

                    val calle = direccionStr.thoroughfare ?: ""
                    val numero = direccionStr.subThoroughfare ?: ""
                    val comuna = direccionStr.locality ?: ""
                    val ciudad = direccionStr.subAdminArea?.takeIf { it.isNotBlank() } ?: comuna
                    val codigoPostal = direccionStr.postalCode ?: ""
                    val region = direccionStr.adminArea ?: ""

                    val regionSigla = obtenerSiglaRegion(direccionStr.adminArea)

                    val calleCompleta = if (numero.isNotBlank()) "$calle $numero" else calle
                    val textoLegible = "$calleCompleta, $ciudad, $region"

                    val direccionParaFormulario = Direccion(
                        calle = calleCompleta,
                        comuna = comuna,
                        ciudad = ciudad,
                        codigoPostal = codigoPostal,
                        region = regionSigla,
                        telefono = "",
                        latitud = location.latitude,
                        longitud = location.longitude
                    )

                    launch(Dispatchers.Main) {
                        _uiState.update { state ->
                            state.copy(textoUbicacionGps = textoLegible)
                        }
                        cambiarDireccionSugerida(direccionParaFormulario)
                        onExito()
                    }
                } else {
                    launch(Dispatchers.Main) {
                        _uiState.update { it.copy(textoUbicacionGps = "No se pudo encontrar la calle.") }
                    }
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    _uiState.update { it.copy(textoUbicacionGps = "Error buscando la dirección.") }
                }
            }
        }
    }

    fun convertirTextoADireccion(texto: String) {

        if (texto.isBlank()) return

        viewModelScope.launch(Dispatchers.IO) {

            launch(Dispatchers.Main) {
                _uiState.update { it.copy(errorDireccionNoEncontrada = false) }
            }

            try {

                val direcciones = geocoder.getFromLocationName(texto, 1)

                if (!direcciones.isNullOrEmpty()) {
                    val address = direcciones[0]

                    val latitud = address.latitude
                    val longitud = address.longitude

                    val comuna = address.locality ?: ""
                    val ciudad = address.subAdminArea?.takeIf { it.isNotBlank() } ?: comuna
                    val codigoPostal = address.postalCode ?: ""
                    val region = address.adminArea ?: ""

                    val regionSigla = obtenerSiglaRegion(region)

                    launch(Dispatchers.Main) {
                        _uiState.update { state ->

                            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(
                                comuna = comuna,
                                ciudad = ciudad,
                                codigoPostal = codigoPostal,
                                region = regionSigla,
                                latitud = latitud,
                                longitud = longitud
                            ) ?: Direccion(
                                calle = texto,
                                comuna = comuna,
                                ciudad = ciudad,
                                codigoPostal = codigoPostal,
                                region = regionSigla,
                                latitud = latitud,
                                longitud = longitud
                            )

                            state.copy(direccionSugeridaGeocoding = direccionActualizada)
                        }
                    }

                } else {
                    launch(Dispatchers.Main) {
                        _uiState.update { it.copy(errorDireccionNoEncontrada = true) }
                    }
                }

            } catch (e: Exception) {
                Log.e("GEOCODER_ERROR", "Error resolviendo texto a coordenadas: ${e.message}")
            }
        }
    }

    fun ejecutarBusquedaEnPlaces(busqueda: String) {

        if (busqueda.isEmpty()) {
            _uiState.update { it.copy(sugerenciasDirecciones = emptyList()) }
            return
        }

        val peticion = FindAutocompletePredictionsRequest.builder()
            .setSessionToken(token)
            .setQuery(busqueda)
            .setTypesFilter(listOf("geocode"))
            .setCountries("CL") //Restringe la busqueda solo a Chile
            .build()

        placesClient.findAutocompletePredictions(peticion)

            .addOnSuccessListener { response ->
                _uiState.update { it.copy(sugerenciasDirecciones = response.autocompletePredictions) }
            }
            .addOnFailureListener { exception ->
                Log.e("PLACES_API_ERROR", "Error buscando lugares: ${exception.message}", exception)
                _uiState.update { it.copy(sugerenciasDirecciones = emptyList()) }
            }
    }

    fun actualizarTextoIngresarDireccion(nuevaDireccion: String) {

        _uiState.update { state ->

            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(
                calle = nuevaDireccion,
                latitud = 0.0,
                longitud = 0.0,
                comuna = "",
                ciudad = "",
                region = ""
            )
                ?: Direccion(calle = nuevaDireccion)

            state.copy(direccionSugeridaGeocoding = direccionActualizada, errorDireccionNoEncontrada = false)
        }
    }

    fun actualizarTextoDetalleDireccion(nuevoDetalle: String) {

        _uiState.update { state ->

            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(detalle = nuevoDetalle)
                ?: Direccion(detalle = nuevoDetalle)

            state.copy(direccionSugeridaGeocoding = direccionActualizada)
        }
    }

    fun actualizarTextoInformacionAdicional(nuevaInformacionAdicional: String) {

        _uiState.update { state ->

            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(informacionAdicional = nuevaInformacionAdicional)
                ?: Direccion(informacionAdicional = nuevaInformacionAdicional)

            state.copy(direccionSugeridaGeocoding = direccionActualizada)
        }
    }

    fun actualizarTextoNombreReceptor(nuevoNombreReceptor: String) {

        _uiState.update { state ->

            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(nombreReceptor = nuevoNombreReceptor)
                ?: Direccion(nombreReceptor = nuevoNombreReceptor)

            state.copy(direccionSugeridaGeocoding = direccionActualizada)
        }
    }

    fun actualizarTextoTelefonoDireccion(nuevoTelefono: String) {

        _uiState.update { state ->

            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(telefono = nuevoTelefono)
                ?: Direccion(telefono = nuevoTelefono)

            state.copy(direccionSugeridaGeocoding = direccionActualizada)
        }
    }

    fun limpiarTokenDeBusqueda() {
        token = AutocompleteSessionToken.newInstance()
    }

    fun limpiarErrorDireccion() {
        _uiState.update { it.copy(errorDireccionNoEncontrada = false) }
    }
}