package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.AutorResena
import cl.bootcamp.leeconnos.data.modelos.Resena
import cl.bootcamp.leeconnos.ui.components.SeccionResenas
import cl.bootcamp.leeconnos.ui.components.botones.BotonIconoDeseados
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.bottombar.BottomBarSecundario
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleFichaProducto
import cl.bootcamp.leeconnos.ui.components.detalles.RecomendacionVideo
import cl.bootcamp.leeconnos.ui.components.imagenes.ImagenFichaLibro
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarFichaLibro
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun FichaLibroScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit,
    onNavigateBack: () -> Unit
) {

    val libro by mainViewModel.libroSeleccionado.collectAsState()
    val listaItems by mainViewModel.itemsListaDeseados.collectAsState()
    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()

    Scaffold(

        bottomBar = {

            BottomBarSecundario(
                content = {
                    BotonPrincipal(
                        textoBtn = "Agregar al carrito",
                        iconoIzquierda = R.drawable.ic_carrito,
                        onClick = {

                            if (libro != null) {
                                mainViewModel.agregarItemCarrito(libro!!)
                            }
                        },
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        contentPadding = PaddingValues(vertical = 12.dp)
                    )
                }
            )
        },

        //contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            libro?.let { libroSeguro ->

                val esDeseado = listaItems.any { it.id == libroSeguro.id }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            ImagenFichaLibro(
                                imagen = libroSeguro.portadaUrl,
                                contentDescription = libroSeguro.titulo
                            )

                            BotonIconoDeseados(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(end = 16.dp, bottom = 16.dp),
                                estaEnListaDeseados = esDeseado,
                                buttonSize = 64.dp,
                                iconSize = 36.dp,
                                onClick = {

                                    if (esDeseado) {
                                        mainViewModel.eliminarItemListaDeseados(libroSeguro.id)

                                    } else {
                                        mainViewModel.agregarItemListaDeseados(libroSeguro)
                                    }
                                }
                            )
                        }


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

                AppTopBarFichaLibro (
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    onNavigateBack = onNavigateBack,
                    onDeseadosClick = { rutaListaDeseados ->
                        onNavigateTo(rutaListaDeseados)
                    },
                    cantidadItemsCarrito = { itemsCarrito.sumOf { it.cantidad } },
                    onCarritoClick = { rutaCarrito ->
                        onNavigateTo(rutaCarrito)
                    }
                )
            }
        }
    }
}