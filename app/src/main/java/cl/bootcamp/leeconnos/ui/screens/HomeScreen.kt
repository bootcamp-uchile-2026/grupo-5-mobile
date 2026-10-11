package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AppTopBarDefault
import cl.bootcamp.leeconnos.ui.components.BannerComunidad
import cl.bootcamp.leeconnos.ui.components.CarruselLibros
import cl.bootcamp.leeconnos.ui.components.CarruselLibrosRecomendados
import cl.bootcamp.leeconnos.ui.components.SeccionBienvenida

@Composable
fun HomeScreen(
    onNavigateTo: (String) -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarDefault(
                onNavigateTo = onNavigateTo
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

                CarruselLibros(
                    titulo = "Más vendidos"
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {

                CarruselLibros(
                    titulo = "Más comentados por la comunidad"
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {

                CarruselLibros(
                    titulo = "Nuevos lanzamientos"
                )

                Spacer(modifier = Modifier.height(64.dp))
            }
        }
    }
}