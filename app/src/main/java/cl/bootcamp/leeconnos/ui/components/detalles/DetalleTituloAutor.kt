package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetalleTituloAutor(
    titulo: String,
    autor: String
) {

    Text(
        text = titulo,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.SemiBold
    )

    Text(
        text = autor,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Light,
        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
    )
}