package cl.bootcamp.leeconnos.ui.screens

import android.Manifest
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Direccion
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.campostexto.CampoTextoCupon
import cl.bootcamp.leeconnos.ui.components.cards.TarjetaMetodoEntrega
import cl.bootcamp.leeconnos.ui.components.desplegables.DesplegableCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleConfirmacionCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleResumenCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleSubtotalCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleTotalCompra
import cl.bootcamp.leeconnos.ui.components.dialogos.DialogoDirecciones
import cl.bootcamp.leeconnos.ui.components.dialogos.DialogoTarjeta
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.CheckoutViewModel
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import cl.bootcamp.leeconnos.viewmodel.MetodoEntrega
import cl.bootcamp.leeconnos.viewmodel.MetodoPago
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ConfirmacionCompraScreen(
    mainViewModel: MainViewModel,
    checkoutViewModel: CheckoutViewModel,
    onNavigateBack: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onPagarClick: () -> Unit
) {

    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()
    val totalCompra by mainViewModel.totalCompra.collectAsState()

    checkoutViewModel.calcularTotalCompra(totalCompra)

    val uiState by checkoutViewModel.uiState.collectAsState()

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
                enabled = uiState.puedePagar,
                onClick = onPagarClick
            )
        }

    ) { paddingValues ->

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {

            Column {
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

                        val subtitulo = when (uiState.metodoEntrega) {

                            MetodoEntrega.NINGUNO -> "*Obligatorio"
                            MetodoEntrega.DELIVERY -> "Delivery"
                            MetodoEntrega.RETIRO_TIENDA -> "Retiro en tienda"
                        }

                        Text(
                            text = subtitulo,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    },
                    contenidoDesplegable = {

                        if (uiState.metodoEntrega == MetodoEntrega.NINGUNO) {

                            DetalleConfirmacionCompra(
                                textoPrimerItem = "Delivery",
                                textoSegundoItem = "Retiro en tienda",
                                iconoPrimerItem = R.drawable.ic_truck,
                                iconoSegundoItem = R.drawable.ic_shopping_bag,
                                primerItemSelected = false,
                                segundoItemSelected = false,
                                primerItemOnClick = {
                                    checkoutViewModel.cambiarMostrarDialogoEntrega(true)
                                },
                                segundoItemOnClick = {
                                    checkoutViewModel.cambiarMostrarDialogoEntrega(true)
                                }
                            )

                        } else {

                            TarjetaMetodoEntrega(
                                onCambiarDireccionClick = {

                                    checkoutViewModel.cambiarMostrarDialogoEntrega(true)
                                    checkoutViewModel.seleccionarMetodoEntrega(MetodoEntrega.NINGUNO)
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

                        DetalleConfirmacionCompra(
                            textoPrimerItem = "Agregar tarjeta crédito / débito",
                            textoSegundoItem = "Transferencia",
                            iconoPrimerItem = R.drawable.ic_credit_card,
                            iconoSegundoItem = R.drawable.ic_shopping_bag,
                            primerItemSelected = uiState.metodoPago == MetodoPago.TARJETA,
                            segundoItemSelected = uiState.metodoPago == MetodoPago.TRANSFERENCIA,
                            primerItemOnClick = {
                                checkoutViewModel.cambiarMostrarDialogoTarjeta(true)
                            },
                            segundoItemOnClick = {
                                checkoutViewModel.cambiarMostrarDialogoTarjeta(true)
                            }
                        )
                    }
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                CampoTextoCupon(
                    texto = uiState.textoCupon,
                    placeholder = "Agregar cupón o gift card",
                    enabled = uiState.metodoPago != MetodoPago.NINGUNO,
                    onValueChange = { nuevoValor ->
                        checkoutViewModel.actualizarTextoCupon(nuevoValor)
                    },
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                DetalleSubtotalCompra(
                    concepto = "Costo de productos",
                    valor = "$$totalCompra"
                )

                if (uiState.descuentoCupon > 0) {
                    DetalleSubtotalCompra(
                        concepto = "Descuento",
                        valor = "$${uiState.descuentoCupon}"
                    )
                }

                DetalleSubtotalCompra(
                    concepto = "Despacho",
                    valor = "$${uiState.costoDespacho}"
                )

                DetalleTotalCompra(
                    concepto = "Total de la compra",
                    valor = "$${uiState.totalCompra}"
                )
            }
        }
    }

    //PopUp para elegir dirección para Delivery
    if (uiState.mostrarDialogoEntrega) {

        Dialog(
            onDismissRequest = { checkoutViewModel.cambiarMostrarDialogoEntrega(false) }
        ) {

            var textoBusqueda by rememberSaveable { mutableStateOf("") }

            DialogoDirecciones(
                textoBusqueda = textoBusqueda,
                tienePermisoUbicacion = locationPermissionsState.allPermissionsGranted,
                debeMostrarRationale = locationPermissionsState.shouldShowRationale,

                onTextoBusquedaChange = { nuevoValor ->

                },

                onNuevaDireccion = { destino ->
                    checkoutViewModel.cambiarMostrarDialogoEntrega(false)
                    onNavigateTo(destino)
                },

                onDireccionGuardada = { direccion ->
                    checkoutViewModel.seleccionarMetodoEntrega(MetodoEntrega.DELIVERY)
                    checkoutViewModel.cambiarMostrarDialogoEntrega(false)
                    checkoutViewModel.confirmarDireccion(direccion)
                },

                onPedirPermiso = { locationPermissionsState.launchMultiplePermissionRequest() },

                onDismiss = { checkoutViewModel.cambiarMostrarDialogoEntrega(false) },

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
    if (uiState.mostrarDialogoTarjeta) {

        Dialog(
            onDismissRequest = { checkoutViewModel.cambiarMostrarDialogoTarjeta(false) }
        ) {

            DialogoTarjeta(
                onTarjetaCreditoClick = {

                    //Por ahora para confirmar funcionamiento de interfaz
                    checkoutViewModel.seleccionarMetodoPago(MetodoPago.TARJETA)
                    checkoutViewModel.cambiarMostrarDialogoTarjeta(false)
                    //onNavigateTo(SecondaryRoutes.AgregarTarjeta.route)
                },
                onTarjetaDebitoPrepagoClick = {
                    onNavigateTo(SecondaryRoutes.AgregarTarjeta.route)
                },
                onDismiss = { checkoutViewModel.cambiarMostrarDialogoTarjeta(false) }
            )
        }
    }
}