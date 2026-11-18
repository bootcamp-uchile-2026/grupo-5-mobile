package cl.bootcamp.leeconnos.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.campostexto.CampoTexto
import cl.bootcamp.leeconnos.ui.components.cards.TarjetaOpcionesEntrega
import cl.bootcamp.leeconnos.ui.components.dropdown.DropdownBusquedaDireccion
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.CheckoutViewModel
import kotlinx.coroutines.launch
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AgregarDireccionScreen(
    checkoutViewModel: CheckoutViewModel,
    onNavigateBack: () -> Unit
) {

    //Limpia el error al salir de la pantalla, ya que al compartir el viewmodel sigue estando el error al navegar
    DisposableEffect(Unit) {
        onDispose {
            checkoutViewModel.limpiarErrorDireccion()
        }
    }

    val uiState by checkoutViewModel.uiState.collectAsState()

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    //Santiago
    val ubicacionPorDefecto = remember(uiState.direccionSugeridaGeocoding) {

        val direccion = uiState.direccionSugeridaGeocoding

        if (direccion != null && direccion.latitud != 0.0) {
            LatLng(direccion.latitud, direccion.longitud)

        } else {
            LatLng(-33.4489, -70.6693)
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubicacionPorDefecto, 16f)
    }

    val markerState = rememberUpdatedMarkerState(position = ubicacionPorDefecto)

    LaunchedEffect(ubicacionPorDefecto) {
        markerState.position = ubicacionPorDefecto
    }

    LaunchedEffect(uiState.direccionSugeridaGeocoding) {

        val direccion = uiState.direccionSugeridaGeocoding

        if (direccion != null && direccion.latitud != 0.0) {

            val nuevaUbicacion = LatLng(direccion.latitud, direccion.longitud)
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(nuevaUbicacion, 16f),
                durationMs = 1000
            )
        }
    }

    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        if (locationPermissionState.allPermissionsGranted) {

            @SuppressLint("MissingPermission")
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {

                    if (uiState.direccionSugeridaGeocoding == null) {

                        val nuevaUbicacion = LatLng(location.latitude, location.longitude)

                        launch {
                            cameraPositionState.animate(
                                update = CameraUpdateFactory.newLatLngZoom(nuevaUbicacion, 16f),
                                durationMs = 1000
                            )
                        }
                    }
                }
            }
        }
    }


    Scaffold(
        topBar = {

            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.AgregarDireccion.title,
                onNavigateBack = onNavigateBack
            )
        },

        bottomBar = {

            BotonPrincipal(
                textoBtn = "Continuar",
                enabled = uiState.puedeGuardarDireccion,
                onClick = {

                    val direccionFinal = uiState.direccionSugeridaGeocoding

                    if (direccionFinal != null) {

                        checkoutViewModel.confirmarDireccion(direccionFinal)

                        onNavigateBack()
                    }
                },
                modifier = Modifier.padding(horizontal = 16.dp),
                estiloTexto = MaterialTheme.typography.bodyMedium,
                contentPadding = PaddingValues(vertical = 12.dp)
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .padding(bottom = 24.dp),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = locationPermissionState.allPermissionsGranted),
                onMapClick = { latLng ->

                    markerState.position = latLng

                    val location = Location("").apply {
                        latitude = latLng.latitude
                        longitude = latLng.longitude
                    }

                    checkoutViewModel.convertirCoordenadasADireccion(
                        location = location,
                        onExito = {

                        }
                    )
                }
            ) {
                Marker(
                    state = markerState,
                    title = "Dirección de entrega"
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState())
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier.onFocusChanged { focusState ->

                            if (!focusState.isFocused) {

                                val calleActual = uiState.direccionSugeridaGeocoding?.calle
                                val latActual = uiState.direccionSugeridaGeocoding?.latitud ?: 0.0

                                if (!calleActual.isNullOrBlank() && latActual == 0.0) {

                                    checkoutViewModel.convertirTextoADireccion(calleActual)
                                }

                                checkoutViewModel.actualizarTextoBusqueda("")
                            }
                        }
                    ){
                        CampoTexto(
                            texto = uiState.direccionSugeridaGeocoding?.calle ?: "",
                            label = "Ingresa una dirección *",
                            onValueChange = { nuevaDireccion ->

                                checkoutViewModel.actualizarTextoIngresarDireccion(nuevaDireccion)

                                //Para mostrar sugerencias de direcciones con Places API
                                checkoutViewModel.actualizarTextoBusqueda(nuevaDireccion)
                            },
                            isError = uiState.errorDireccionNoEncontrada,
                            mensajeError = "No pudimos encontrar esta dirección. Por favor, selecciona una sugerencia o usa el mapa."
                        )
                    }

                    DropdownBusquedaDireccion(
                        visible = uiState.sugerenciasDirecciones.isNotEmpty() &&
                                uiState.direccionSugeridaGeocoding?.calle?.isNotEmpty() == true,
                        listaOpciones = uiState.sugerenciasDirecciones,
                        onOpcionClick = { prediccion ->

                            checkoutViewModel.actualizarTextoBusqueda("")

                            checkoutViewModel.seleccionarDireccionDesdePrediccion(
                                prediccion = prediccion,
                                onExito = {}
                            )
                        }
                    )


                    CampoTexto(
                        texto = uiState.direccionSugeridaGeocoding?.detalle ?: "",
                        label = "Detalle: casa, piso, torre",
                        onValueChange = { nuevoDetalle ->
                            checkoutViewModel.actualizarTextoDetalleDireccion(nuevoDetalle)
                        }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {

                    Text(
                        text = "Opciones de entrega",
                        style = MaterialTheme.typography.bodyMedium,
                        //modifier = Modifier.padding(bottom = 8.dp)
                    )

                    //Posiblemente incluir esto dentro de un lazycolumn para varias opciones de entrega, por ahora por wireframe solo una opción
                    TarjetaOpcionesEntrega(
                        selected = true,
                        onClick = {}
                    )

                    CampoTexto(
                        texto = uiState.direccionSugeridaGeocoding?.informacionAdicional ?: "",
                        label = "Información adicional",
                        onValueChange = { nuevaInformacionAdicional ->
                            checkoutViewModel.actualizarTextoInformacionAdicional(
                                nuevaInformacionAdicional
                            )
                        }
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "Datos",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    CampoTexto(
                        texto = uiState.direccionSugeridaGeocoding?.nombreReceptor ?: "",
                        label = "Nombre de quien recibe *",
                        onValueChange = { nuevoNombreReceptor ->
                            checkoutViewModel.actualizarTextoNombreReceptor(nuevoNombreReceptor)
                        }
                    )

                    CampoTexto(
                        texto = uiState.direccionSugeridaGeocoding?.telefono ?: "",
                        label = "Teléfono *",
                        onValueChange = { nuevoTelefono ->
                            checkoutViewModel.actualizarTextoTelefonoDireccion(nuevoTelefono)
                        }
                    )
                }
            }
        }
    }
}