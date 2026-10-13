package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.components.BottomBarDetalleCompra
import cl.bootcamp.leeconnos.ui.components.CantidadItems
import cl.bootcamp.leeconnos.ui.components.ItemCarrito
import cl.bootcamp.leeconnos.ui.components.ItemListaDeseados
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun ListaDeseadosScreen(
    onNavigateBack: () -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.ListaDeseados.title,
                onNavigateBack = { onNavigateBack() }
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
                    cantidad = 3
                )

                Spacer(modifier = Modifier.height(24.dp))
            }

            items(count = 3) {

                ItemListaDeseados(
                    onDeleteClick = {}
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}