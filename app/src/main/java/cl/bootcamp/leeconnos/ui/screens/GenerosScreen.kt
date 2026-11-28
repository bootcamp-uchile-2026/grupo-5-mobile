package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.ItemGenero
import cl.bootcamp.leeconnos.ui.components.bottombar.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarDefault
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun GenerosScreen(
    mainViewModel: MainViewModel,
    onNavigateBottomBar: (String) -> Unit,
    onNavigateTo: (String) -> Unit
){
    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarDefault(
                cantidadItems = { itemsCarrito.sumOf { it.cantidad } },
                onNavigateTo = onNavigateTo,
                placeHolderBuscador = "Busca tu libro aquí"
            )
        },

        bottomBar = {
            AppBottomBar(
                rutaActual = ScreenRoutes.GENEROS.route,
                onNavigateTo = onNavigateBottomBar
            )
        },

        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        //TODO: Cambiar por obtenerlos desde viewmodel -> repositorio -> Room/API
        val generosDummy = listOf("Ficción", "No Ficción", "Arte y Diseño", "Bienestar", "Negocios")

        LazyVerticalGrid(
            columns = GridCells.Fixed(2), //Siempre como máximo 2 columnas
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {

            items(generosDummy) { genero ->

                ItemGenero(
                    imagen = R.drawable.genero_ficcion,
                    nombre = genero
                )
            }
        }
    }
}