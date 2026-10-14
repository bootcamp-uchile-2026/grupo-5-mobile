package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AppTopBarDefault
import cl.bootcamp.leeconnos.ui.components.BannerComunidad
import cl.bootcamp.leeconnos.ui.components.CarruselLibros
import cl.bootcamp.leeconnos.ui.components.CarruselLibrosRecomendados
import cl.bootcamp.leeconnos.ui.components.SeccionBienvenida
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit
){

    val cantidadItemsCarrito by mainViewModel.cantidadItemsCarrito.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarDefault(
                onNavigateTo = onNavigateTo,
                cantidadItems = { cantidadItemsCarrito },
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            item {
                SeccionBienvenida(
                    nombre = "Maxi"//TODO: Cambiar por DataSource
                )

                Spacer(modifier = Modifier.height(24.dp))
            }


            item {

                CarruselLibrosRecomendados(
                    titulo = "Recomendados de nuestros libreros"
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {

                BannerComunidad()

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {

                //Toda esta lógica es solo para la simulación, cada objeto debería tener su atributo
                var estaEnListaDeseados by remember { mutableStateOf(false) }

                CarruselLibros(
                    titulo = "Más vendidos",
                    estaEnListaDeseados = estaEnListaDeseados,
                    onAddClick = {
                        mainViewModel.agregarItemListaDeseados()
                        estaEnListaDeseados = true
                    }
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {

                var estaEnListaDeseados by remember { mutableStateOf(false) }

                CarruselLibros(
                    titulo = "Más comentados por la comunidad",
                    estaEnListaDeseados = estaEnListaDeseados,
                    onAddClick = {
                        mainViewModel.agregarItemListaDeseados()
                        estaEnListaDeseados = true
                    }
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {

                var estaEnListaDeseados by remember { mutableStateOf(false) }

                CarruselLibros(
                    titulo = "Nuevos lanzamientos",
                    estaEnListaDeseados = estaEnListaDeseados,
                    onAddClick = {
                        mainViewModel.agregarItemListaDeseados()
                        estaEnListaDeseados = true
                    }
                )

                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}