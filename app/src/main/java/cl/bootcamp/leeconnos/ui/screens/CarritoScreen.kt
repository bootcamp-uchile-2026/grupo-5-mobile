package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.ui.components.AlertaConfirmacion
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.components.bottombar.BottomBarDetalleCompra
import cl.bootcamp.leeconnos.ui.components.carruseles.CantidadItems
import cl.bootcamp.leeconnos.ui.components.carruseles.ItemCarrito
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    mainViewModel: MainViewModel,
    onNavigateBack: () -> Unit
){
    var libroAEliminar by remember { mutableStateOf<Libro?>(null) }
    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()

    val ahorroCompra by mainViewModel.ahorroCompra.collectAsState()
    val totalCompra by mainViewModel.totalCompra.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.Carrito.title,
                onNavigateBack = { onNavigateBack() }
            )
        },

        bottomBar = {

            BottomBarDetalleCompra(
                textoBtn = "Comprar",
                ahorro = ahorroCompra,
                total = totalCompra
            )
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
                    cantidadItems = { itemsCarrito.size }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            //Reemplazar luego con lista de libros
            items(itemsCarrito) { item ->

                ItemCarrito(
                    libro = item,
                    onDeleteClick = {
                        libroAEliminar = item
                    },
                    onIncrease = {

                        if(item.cantidad < 99){
                            mainViewModel.agregarItemCarrito(item)
                        }
                    },
                    onDecrease = {

                        if (item.cantidad > 1) {

                            mainViewModel.disminuirCantidadItemCarrito(item)

                        } else {
                            libroAEliminar = item
                        }
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    libroAEliminar?.let { libro ->


        AlertaConfirmacion(
            titulo = "",
            pregunta = "¿Seguro que quieres eliminar este artículo?",
            textoBtnConfirmar = "Eliminar",
            textoBtnCancelar = "Cancelar",
            onCancelar = {
                libroAEliminar = null
            },
            onConfirmar = {

                mainViewModel.eliminarItemCarrito(libroId = libro.id)

                libroAEliminar = null
            }
        )
    }
}