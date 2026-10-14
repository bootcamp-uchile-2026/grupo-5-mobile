package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Libro

@Composable
fun DetalleTarjetaLibro(
    libro: Libro
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = libro.titulo,
            style = MaterialTheme.typography.bodySmall,
            minLines = 2,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(bottom = 4.dp)
        )

        Text(
            text = libro.autor,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Light,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "$ ${libro.precio}",
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = "30% dcto.",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.error,
                        shape = MaterialTheme.shapes.extraSmall
                    )
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }

        Text(
            text = "$ 10000",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Light,
            textDecoration = TextDecoration.LineThrough
        )

        ValoracionDetalleTarjetaLibro(
            valoracion = "",
            cantidadOpiniones = 12
        )
    }
}