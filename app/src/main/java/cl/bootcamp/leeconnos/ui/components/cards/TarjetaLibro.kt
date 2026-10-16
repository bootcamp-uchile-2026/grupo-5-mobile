package cl.bootcamp.leeconnos.ui.components.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleTarjetaLibro
import cl.bootcamp.leeconnos.ui.components.botones.BotonFlotanteAgregarListaDeseados
import cl.bootcamp.leeconnos.ui.components.imagenes.ImagenTarjetaLibro

@Composable
fun TarjetaLibro(
    libro: Libro,
    onCardClick: () -> Unit,
    onDeseadosClick: () -> Unit
){

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.width(180.dp),
            onClick = { onCardClick() }
        ) {
            Column {

                Box {
                    ImagenTarjetaLibro(
                        imagen = libro.portadaUrl,
                        contentDescription = "Titulo del libro",
                        aspectRatio = 1f
                    )

                    BotonFlotanteAgregarListaDeseados(

                        libro = libro,
                        modifier = Modifier.align(Alignment.BottomEnd),
                        onIconButtonClick = onDeseadosClick
                    )
                }

                DetalleTarjetaLibro(
                    libro = libro
                )
            }
        }

    }
