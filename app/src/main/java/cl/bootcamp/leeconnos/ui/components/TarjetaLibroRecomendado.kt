package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun TarjetaLibroRecomendado(
    //Aquí recibir objeto libro
){

    Box(
    ) {

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
            shape = MaterialTheme.shapes.large,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            modifier = Modifier.width(180.dp)
        ) {
            Column {

                ImagenTarjetaLibro(
                    imagen = R.drawable.fondo_transparente,
                    contentDescription = "Titulo del libro"
                )

                DetalleTarjetaLibroRecomendado(
                    titulo = "Titulo del libro",
                    autor = "Autor",
                    valoracion = "4.5",
                    cantidadOpiniones = "12"
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