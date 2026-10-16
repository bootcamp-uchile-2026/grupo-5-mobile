package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleFichaProducto
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSoloDeseados
import cl.bootcamp.leeconnos.ui.components.imagenes.ImagenFichaLibro
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun FichaLibroScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit,
    onNavigateBack: () -> Unit
){

    val libro by mainViewModel.libroSeleccionado.collectAsState()

    val listaItems by mainViewModel.itemsListaDeseados.collectAsState()

    Scaffold { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {

            libro?.let { libroSeguro ->

                val esDeseado = listaItems.any { it.id == libroSeguro.id }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {

                        ImagenFichaLibro(
                            imagen = libroSeguro.portadaUrl,
                            contentDescription = libroSeguro.titulo
                        )

                        Spacer(modifier = Modifier.height(32.dp))
                    }

                    item {

                        DetalleFichaProducto(
                            libro = libroSeguro
                        )
                    }
                }

                AppTopBarSoloDeseados(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .statusBarsPadding(),
                    estaEnListaDeseados = esDeseado,
                    onNavigateBack = onNavigateBack,
                    onDeseadosClick = {

                        if (esDeseado) {
                            mainViewModel.eliminarItemListaDeseados(libroSeguro.id)

                        } else {
                            mainViewModel.agregarItemListaDeseados(libroSeguro)
                        }
                    }
                )
            }
        }
    }
}