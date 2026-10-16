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
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleTarjetaLibroRecomendado
import cl.bootcamp.leeconnos.ui.components.detalles.EtiquetaLibrero
import cl.bootcamp.leeconnos.ui.components.imagenes.ImagenTarjetaLibro

@Composable
fun TarjetaLibroRecomendado(
    libro: Libro,
    onCardClick: () -> Unit
){

    Box(
    ) {

        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.width(180.dp),
            onClick = { onCardClick() }
        ) {
            Column {

                ImagenTarjetaLibro(
                    imagen = libro.portadaUrl,
                    contentDescription = libro.titulo
                )

                DetalleTarjetaLibroRecomendado(
                    titulo = libro.titulo,
                    autor = libro.autor,
                    valoracion = libro.calificacion,
                    cantidadOpiniones = libro.cantidadOpiniones
                )
            }
        }

        EtiquetaLibrero(
            nombre = "Gregorio S.",
            foto = R.drawable.fondo_transparente,
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}