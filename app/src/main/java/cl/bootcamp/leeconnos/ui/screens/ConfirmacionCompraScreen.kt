package cl.bootcamp.leeconnos.ui.screens

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Direccion
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.cards.TarjetaMetodoEntrega
import cl.bootcamp.leeconnos.ui.components.desplegables.DesplegableCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleConfirmacionCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleResumenCompra
import cl.bootcamp.leeconnos.ui.components.dialogos.DialogoDirecciones
import cl.bootcamp.leeconnos.ui.components.dialogos.DialogoTarjeta
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ConfirmacionCompraScreen(
    mainViewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onPagarClick: () -> Unit
) {

    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()
    val totalCompra by mainViewModel.totalCompra.collectAsState()

    var mostrarDialogoDelivery by rememberSaveable { mutableStateOf(false) }
    var metodoEntregaElegido by rememberSaveable { mutableStateOf(false) }

    var mostrarDialogoTarjeta by rememberSaveable { mutableStateOf(false) }

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    Scaffold(
        topBar = {
            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.ConfirmacionCompra.title,
                onNavigateBack = onNavigateBack
            )
        },

        bottomBar = {

            BotonPrincipalBottomBar(
                textoBtn = "Pagar",
                onClick = onPagarClick
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {

            //Resumen
            DesplegableCompra(
                icono = R.drawable.ic_shopping_bag,
                nombre = "Resumen",
                resultado = {
                    Text(
                        text = "(${itemsCarrito.size}) $${totalCompra}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                contenidoDesplegable = {

                    DetalleResumenCompra(
                        listaLibros = itemsCarrito
                    )
                }
            )

            //Métodos de entrega
            DesplegableCompra(
                icono = R.drawable.ic_truck,
                nombre = "Método de entrega",
                resultado = {

                    val subtitulo = if (!metodoEntregaElegido) "*Obligatorio" else "Delivery"

                    Text(
                        text = subtitulo,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                contenidoDesplegable = {

                    if (!metodoEntregaElegido) {

                        var deliverySelected by rememberSaveable { mutableStateOf(false) }
                        var retiroSelected by rememberSaveable { mutableStateOf(false) }

                        DetalleConfirmacionCompra(
                            textoPrimerItem = "Delivery",
                            textoSegundoItem = "Retiro en tienda",
                            iconoPrimerItem = R.drawable.ic_truck,
                            iconoSegundoItem = R.drawable.ic_shopping_bag,
                            primerItemSelected = deliverySelected,
                            segundoItemSelected = retiroSelected,
                            primerItemOnClick = {

                                retiroSelected = false
                                deliverySelected = true

                                mostrarDialogoDelivery = true
                            },
                            segundoItemOnClick = {
                                deliverySelected = false
                                retiroSelected = true
                            }
                        )

                    } else {

                        TarjetaMetodoEntrega(
                            onCambiarDireccionClick = {
                                metodoEntregaElegido = false
                                mostrarDialogoDelivery = true
                            }
                        )
                    }
                }
            )

            //Métodos de pago
            DesplegableCompra(
                icono = R.drawable.ic_credit_card,
                nombre = "Métodos de pago",
                resultado = {},
                contenidoDesplegable = {

                    var tarjetaSelected by rememberSaveable { mutableStateOf(false) }
                    var transferenciaSelected by rememberSaveable { mutableStateOf(false) }

                    DetalleConfirmacionCompra(
                        textoPrimerItem = "Agregar tarjeta crédito / débito",
                        textoSegundoItem = "Transferencia",
                        iconoPrimerItem = R.drawable.ic_credit_card,
                        iconoSegundoItem = R.drawable.ic_shopping_bag,
                        primerItemSelected = tarjetaSelected,
                        segundoItemSelected = transferenciaSelected,
                        primerItemOnClick = {
                            tarjetaSelected = true
                            transferenciaSelected = false

                            mostrarDialogoTarjeta = true
                        },
                        segundoItemOnClick = {
                            transferenciaSelected = true
                            tarjetaSelected = false
                        }
                    )
                }
            )
        }
    }

    //PopUp para elegir dirección para Delivery
    if (mostrarDialogoDelivery) {

        Dialog(
            onDismissRequest = { mostrarDialogoDelivery = false }
        ) {

            var textoBusqueda by rememberSaveable { mutableStateOf("") }

            DialogoDirecciones(
                textoBusqueda = textoBusqueda,
                tienePermisoUbicacion = locationPermissionsState.allPermissionsGranted,
                debeMostrarRationale = locationPermissionsState.shouldShowRationale,

                onTextoBusquedaChange = { nuevoValor ->
                    mostrarDialogoDelivery = false
                    textoBusqueda = nuevoValor
                },

                onNuevaDireccion = { destino ->
                    mostrarDialogoDelivery = false
                    onNavigateTo(destino)
                },

                onDireccionGuardada = {
                    mostrarDialogoDelivery = false
                    metodoEntregaElegido = true
                },

                onPedirPermiso = { locationPermissionsState.launchMultiplePermissionRequest() },

                onDismiss = { mostrarDialogoDelivery = false },

                listaDirecciones = listOf(
                    Direccion(
                        calle = "Calle 1444",
                        comuna = "Copiapó",
                        region = "Atacama",
                        telefono = "+56911223344"
                    ),

                    Direccion(
                        calle = "Calle 1444",
                        comuna = "Copiapó",
                        region = "Atacama",
                        telefono = "+56911223344"
                    )
                )
            )
        }
    }

    //PopUp para elegir tarjeta
    if (mostrarDialogoTarjeta) {

        Dialog(
            onDismissRequest = { mostrarDialogoTarjeta = false }
        ) {

            DialogoTarjeta(
                onTarjetaCreditoClick = {

                },
                onTarjetaDebitoPrepagoClick = {
                    
                },
                onDismiss = { mostrarDialogoTarjeta = false }
            )
        }
    }
}