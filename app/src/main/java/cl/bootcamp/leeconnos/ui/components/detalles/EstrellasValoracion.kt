package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun EstrellasValoracion(
    valoracion: Float,
    texto: String,
    modifier: Modifier = Modifier
) {

    //Lógica pendiente en base a valoración

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        repeat(5) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star_filled),
                contentDescription = "Estrella valoración",
                modifier = modifier
            )
        }


        Text(
            text = texto,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(start = 4.dp)
        )

    }
}