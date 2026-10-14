package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AlertaConfirmacion
import cl.bootcamp.leeconnos.ui.components.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.components.BottomBarDetalleCompra
import cl.bootcamp.leeconnos.ui.components.CantidadItems
import cl.bootcamp.leeconnos.ui.components.ItemCarrito
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    mainViewModel: MainViewModel,
    onNavigateBack: () -> Unit
){
    var mostrarDialogo by remember { mutableStateOf(false) }
    val cantidad by mainViewModel.cantidadItemsCarrito.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.Carrito.title,
                onNavigateBack = { onNavigateBack() }
            )
        },

        bottomBar = {

            BottomBarDetalleCompra()
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                CantidadItems(
                    cantidadItems = { cantidad }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            //Reemplazar luego con lista de libros
            items(count = cantidad) {

                ItemCarrito(
                    onDeleteClick = {
                        mostrarDialogo = true
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    if (mostrarDialogo) {

        AlertaConfirmacion(
            titulo = "",
            pregunta = "¿Seguro que quieres eliminar este artículo?",
            textoBtnConfirmar = "Eliminar",
            textoBtnCancelar = "Cancelar",
            onCancelar = {
                mostrarDialogo = false
            },
            onConfirmar = {
                mainViewModel.eliminarItemCarrito()

                mostrarDialogo = false
            }
        )
    }
}