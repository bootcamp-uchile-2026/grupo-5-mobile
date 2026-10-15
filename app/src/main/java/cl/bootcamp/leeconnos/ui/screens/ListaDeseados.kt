package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AppTopBarSoloCarrito
import cl.bootcamp.leeconnos.ui.components.CantidadItems
import cl.bootcamp.leeconnos.ui.components.ItemListaDeseados
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun ListaDeseadosScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit,
    onNavigateBack: () -> Unit
){
    val listaCarrito by mainViewModel.itemsCarrito.collectAsState()
    val listaItems by mainViewModel.itemsListaDeseados.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarSoloCarrito(
                titulo = SecondaryRoutes.ListaDeseados.title,
                cantidadItems = {
                    listaCarrito.size
                },
                onNavigateTo = onNavigateTo,
                onNavigateBack = onNavigateBack
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
                    cantidadItems = { listaItems.size }
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            items(listaItems) { item ->

                ItemListaDeseados(
                    libro = item,
                    onDeleteClick = {
                        mainViewModel.eliminarItemListaDeseados(libroId = item.id)
                    },
                    onAddClick = {
                        mainViewModel.agregarItemCarrito(
                            libro = item
                        )
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}