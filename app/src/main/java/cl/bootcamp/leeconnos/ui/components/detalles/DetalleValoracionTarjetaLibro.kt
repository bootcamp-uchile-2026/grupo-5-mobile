package cl.bootcamp.leeconnos.ui.components.detalles

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
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun DetalleValoracionTarjetaLibro(
    valoracion: String,
    cantidadOpiniones: Int
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star_filled), // TODO: Icono según valoración
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = "($cantidadOpiniones opiniones)",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}