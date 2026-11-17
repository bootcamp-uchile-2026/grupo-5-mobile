package cl.bootcamp.leeconnos.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.campostexto.CampoTexto
import cl.bootcamp.leeconnos.ui.components.cards.TarjetaOpcionesEntrega
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.CheckoutViewModel
import kotlinx.coroutines.launch
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AgregarDireccionScreen(
    onNavigateBack: () -> Unit,
    onContinuarClick: () -> Unit
) {

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    //Santiago
    val ubicacionPorDefecto = LatLng(-33.4489, -70.6693)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubicacionPorDefecto, 15f)
    }

    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        if (locationPermissionState.allPermissionsGranted) {
            @SuppressLint("MissingPermission")
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
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



    Scaffold(
        topBar = {

            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.AgregarDireccion.title,
                onNavigateBack = onNavigateBack
            )
        },

        bottomBar = {

            BotonPrincipalBottomBar(
                textoBtn = "Continuar",
                enabled = true,
                onClick = {}
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
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = locationPermissionState.allPermissionsGranted)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    CampoTexto(
                        texto = "",
                        label = "Ingresa una dirección",
                        onValueChange = {}
                    )

                    CampoTexto(
                        texto = "",
                        label = "Detalle: casa, piso, torre",
                        onValueChange = {}
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {

                    Text(
                        text = "Opciones de entrega",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    //Posiblemente incluir esto dentro de un lazycolumn para varias opciones de entrega, por ahora por wireframe solo una opción
                    TarjetaOpcionesEntrega(
                        selected = true,
                        onClick = {}
                    )

                    CampoTexto(
                        texto = "",
                        label = "Información adicional",
                        onValueChange = {},
                        modifier = Modifier.padding(vertical = 8.dp)
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
                        texto = "",
                        label = "Nombre de quien recibe",
                        onValueChange = {}
                    )

                    CampoTexto(
                        texto = "",
                        label = "Teléfono",
                        onValueChange = {}
                    )
                }
            }
        }
    }
}