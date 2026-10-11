package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun DetalleTarjetaLibroRecomendado(
    titulo: String,
    autor: String,
    valoracion: String,
    cantidadOpiniones: String
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = autor,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        ValoracionDetalleTarjetaLibro(
            valoracion = "",
            cantidadOpiniones = 12
        )
    }
}