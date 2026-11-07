package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.data.modelos.AutorResena
import cl.bootcamp.leeconnos.data.modelos.Resena
import cl.bootcamp.leeconnos.ui.components.SeccionResenas
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleFichaProducto
import cl.bootcamp.leeconnos.ui.components.detalles.RecomendacionVideo
import cl.bootcamp.leeconnos.ui.components.imagenes.ImagenFichaLibro
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSoloDeseados
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun FichaLibroScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit,
    onNavigateBack: () -> Unit
){

    val libro by mainViewModel.libroSeleccionado.collectAsState()

    val listaItems by mainViewModel.itemsListaDeseados.collectAsState()

    Scaffold(

        bottomBar = {

            BotonPrincipalBottomBar(
                textoBtn = "Agregar al carrito",
                onClick = {

                    if (libro != null ){
                        mainViewModel.agregarItemCarrito(libro!!)
                    }
                }
            )
        }

    ) { paddingValues ->

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

                        Spacer(modifier = Modifier.height(32.dp))
                    }

                    item {

                        RecomendacionVideo(
                            mood = "oscuro, sensorial e inquietante",
                            descripcion = "El perfume te mete en la mente de un personaje tan fascinante como perturbador. Una historia de obsesión, crimen y deseo, donde los olores tienen tanto peso como los propios personajes"
                        )

                        HorizontalDivider(modifier = Modifier.padding(vertical = 32.dp))
                    }

                    item {

                        SeccionResenas(
                            resena = Resena(
                                id = "1",
                                autor = AutorResena(
                                    id = "1",
                                    nombre = "Daniela S.",
                                    avatarUrl = "",
                                    rol = "CLIENTE"
                                ),
                                tipo = "LECTOR",
                                calificacion = 5.0,
                                titulo = "",
                                comentario = "Excelente libro, la película le hace honor a la historia original, recomendable.",
                                verificada = true,
                                fecha = "",
                                creadoEn = "",
                                actualizadoEn = "",
                                usuarioId = "",
                                libroId = ""
                            ),
                            onNavigateTo = onNavigateTo
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