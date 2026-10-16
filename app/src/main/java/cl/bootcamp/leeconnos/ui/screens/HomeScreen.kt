package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarDefault
import cl.bootcamp.leeconnos.ui.components.BannerComunidad
import cl.bootcamp.leeconnos.ui.components.carruseles.CarruselLibros
import cl.bootcamp.leeconnos.ui.components.carruseles.CarruselLibrosRecomendados
import cl.bootcamp.leeconnos.ui.components.SeccionBienvenida
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.HomeUiState
import cl.bootcamp.leeconnos.viewmodel.HomeViewModel
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    viewModel: HomeViewModel = viewModel(),
    onNavigateTo: (String) -> Unit
){

    val estadoUi by viewModel.uiState.collectAsState()

    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()
    val itemsListaDeseados by mainViewModel.itemsListaDeseados.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarDefault(
                onNavigateTo = onNavigateTo,
                cantidadItems = { itemsCarrito.size },
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

            when (val estadoActual = estadoUi) {

                is HomeUiState.Loading -> {

                    item {
                        Text("Loading")
                    }
                }

                is HomeUiState.Error -> {

                    item {
                        Text("Error")
                    }
                }

                is HomeUiState.Success -> {

                    val recomendadosLibreros = estadoActual.recomendadosLibreros
                    val masVendidos = estadoActual.masVendidos
                    val masComentados = estadoActual.masComentados
                    val nuevosLanzamientos = estadoActual.nuevosLanzamientos

                    item {

                        CarruselLibrosRecomendados(
                            titulo = "Recomendados de nuestros libreros",
                            libros = recomendadosLibreros,
                            onCardClick = { libro ->
                                mainViewModel.seleccionarLibro(libro = libro)
                                onNavigateTo(SecondaryRoutes.FichaLibro.route)
                            }
                        )

                        Spacer(modifier = Modifier.height(64.dp))
                    }

                    item {

                        BannerComunidad()

                        Spacer(modifier = Modifier.height(64.dp))
                    }

                    item {

                        CarruselLibros(
                            titulo = "Más vendidos",
                            libros = masVendidos,
                            itemsDeseados = itemsListaDeseados,
                            onCardClick = { libro ->
                                mainViewModel.seleccionarLibro(libro)
                                onNavigateTo(SecondaryRoutes.FichaLibro.route)
                            },
                            onDeseadosClick = { libro, esDeseado ->

                                if (esDeseado) {
                                    mainViewModel.eliminarItemListaDeseados(libro.id)

                                } else {
                                    mainViewModel.agregarItemListaDeseados(libro)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(64.dp))
                    }

                    item {

                        CarruselLibros(
                            titulo = "Más comentados por la comunidad",
                            libros = masComentados,
                            itemsDeseados = itemsListaDeseados,
                            onCardClick = { libro ->
                                mainViewModel.seleccionarLibro(libro)
                                onNavigateTo(SecondaryRoutes.FichaLibro.route)
                            },
                            onDeseadosClick = { libro, esDeseado ->

                                if (esDeseado) {
                                    mainViewModel.eliminarItemListaDeseados(libro.id)

                                } else {
                                    mainViewModel.agregarItemListaDeseados(libro)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(64.dp))
                    }

                    item {

                        CarruselLibros(
                            titulo = "Nuevos lanzamientos",
                            libros = nuevosLanzamientos,
                            itemsDeseados = itemsListaDeseados,
                            onCardClick = { libro ->
                                mainViewModel.seleccionarLibro(libro)
                                onNavigateTo(SecondaryRoutes.FichaLibro.route)
                            },
                            onDeseadosClick = { libro, esDeseado ->

                                if (esDeseado) {
                                    mainViewModel.eliminarItemListaDeseados(libro.id)

                                } else {
                                    mainViewModel.agregarItemListaDeseados(libro)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(64.dp))
                    }
                }
            }
        }
    }
}