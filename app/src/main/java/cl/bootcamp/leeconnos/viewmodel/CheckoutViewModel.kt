package cl.bootcamp.leeconnos.viewmodel

import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.bootcamp.leeconnos.data.modelos.Direccion
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.data.repositorio.LibroRepository
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    val direccionSugeridaGeocoding: Direccion? = null
){
    val puedePagar: Boolean
        get() = metodoEntrega != MetodoEntrega.NINGUNO && metodoPago != MetodoPago.NINGUNO
}

class CheckoutViewModel(
    private val libroRepository: LibroRepository,
    private val placesClient: PlacesClient,
    private val geocoder: Geocoder
) : ViewModel() {

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

    fun confirmarDireccion(direccion: Direccion) {

        _uiState.update { state ->
            state.copy(
                direccionSeleccionada = direccion,
                mostrarDialogoEntrega = false,
                costoDespacho = 4000 //Por ahora hardcodeado, cambiar cuando haya validación a futuro
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
            //Hay resultados que coinciden
            .addOnSuccessListener { response ->
                _uiState.update { it.copy(sugerenciasDirecciones = response.autocompletePredictions) }
            }
            //Sin resultados que coincidan
            .addOnFailureListener { exception ->
                Log.e("PLACES_API_ERROR", "Error buscando lugares: ${exception.message}", exception)
                _uiState.update { it.copy(sugerenciasDirecciones = emptyList()) }
            }
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
                var comuna = ""
                var region = ""

                place.addressComponents?.asList()?.forEach { component ->
                    if (component.types.contains("route")) calle = component.name
                    if (component.types.contains("street_number")) calle += " ${component.name}"
                    if (component.types.contains("locality")) comuna = component.name
                    if (component.types.contains("administrative_area_level_1")) region = component.name
                }

                if (coordenadas != null) {

                    val nuevaDireccion = Direccion(
                        calle = calle.ifEmpty { prediccion.getPrimaryText(null).toString() },
                        comuna = comuna,
                        region = region,
                        latitud = coordenadas.latitude,
                        longitud = coordenadas.longitude
                    )

                    cambiarDireccionSugerida(nuevaDireccion)
                    cambiarMostrarDialogoEntrega(false)

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
                    val ciudad = direccionStr.locality ?: ""
                    val region = direccionStr.adminArea ?: ""

                    val textoLegible = "$calle $numero, $ciudad, $region"

                    val direccionParaFormulario = Direccion(
                        calle = "$calle $numero",
                        comuna = ciudad,
                        region = region,
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

    fun actualizarTextoIngresarDireccion(nuevaDireccion: String) {

        _uiState.update { state ->

            val direccionActualizada = state.direccionSugeridaGeocoding?.copy(calle = nuevaDireccion)
                ?: Direccion(calle = nuevaDireccion)

            state.copy(direccionSugeridaGeocoding = direccionActualizada)
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
}