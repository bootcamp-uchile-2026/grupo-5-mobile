package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.Buscador
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

    var mostrarDialogoDirecciones by rememberSaveable { mutableStateOf(false) }

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

                            mostrarDialogoDirecciones = true
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

    //PopUp para elegir dirección
    if (mostrarDialogoDirecciones) {

        Dialog(
            onDismissRequest = { mostrarDialogoDirecciones = false }
        ) {

            var textoBusqueda by rememberSaveable { mutableStateOf("") }

            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {

                        Spacer(modifier = Modifier.weight(.2f))

                        Text(
                            text = "Direcciones",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(.6f)
                        )

                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Light,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(.2f)
                        )
                    }

                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                    Buscador(
                        valor = textoBusqueda,
                        placeholder = "Ingresa una dirección",
                        onValueChange = {},
                        onBuscarClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 50.dp)
                            .padding(horizontal = 16.dp)
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))


                }
            }
        }
    }
}