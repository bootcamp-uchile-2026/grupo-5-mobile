package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun ValoracionFichaLibro(
    valoracion: Float,
    opiniones: Int
) {

    //Lógica pendiente en base a valoración

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {

        items(5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = "Estrella valoración",
                modifier = Modifier.padding(end = 4.dp)
            )
        }

        item {
            Text(
                text = "($opiniones)",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
    }
}