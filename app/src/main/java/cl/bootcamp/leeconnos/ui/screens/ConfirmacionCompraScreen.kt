package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.desplegables.DesplegableCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleConfirmacionCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleResumenCompra
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun ConfirmacionCompraScreen(
    mainViewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    onNavigateTo: (String) -> Unit,
    onPagarClick: () -> Unit
) {

    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()
    val totalCompra by mainViewModel.totalCompra.collectAsState()

    Scaffold(
        topBar = {

            AppTopBarSimpleNavigation(
                titulo = "Confirmación de compra",
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

            DesplegableCompra(
                icono = R.drawable.ic_truck,
                nombre = "Método de entrega",
                resultado = {
                    Text(
                        text = "*Obligatorio",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                },
                contenidoDesplegable = {

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
                        },
                        segundoItemOnClick = {
                            deliverySelected = false
                            retiroSelected = true
                        }
                    )
                }
            )

            DesplegableCompra(
                icono = R.drawable.ic_credit_card,
                nombre = "Métodos de pago",
                resultado = {},
                contenidoDesplegable = {

                    var tarjetaSelected by rememberSaveable { mutableStateOf(false) }
                    var transferenciaSelected by rememberSaveable { mutableStateOf(false) }

                    DetalleConfirmacionCompra(
                        textoPrimerItem = "Agregar Tarjeta Crédito / Débito",
                        textoSegundoItem = "Transferencia",
                        iconoPrimerItem = R.drawable.ic_credit_card,
                        iconoSegundoItem = R.drawable.ic_shopping_bag,
                        primerItemSelected = tarjetaSelected,
                        segundoItemSelected = transferenciaSelected,
                        primerItemOnClick = {
                            tarjetaSelected = false
                            transferenciaSelected = true
                        },
                        segundoItemOnClick = {
                            tarjetaSelected = false
                            transferenciaSelected = true
                        }
                    )
                }
            )
        }
    }
}