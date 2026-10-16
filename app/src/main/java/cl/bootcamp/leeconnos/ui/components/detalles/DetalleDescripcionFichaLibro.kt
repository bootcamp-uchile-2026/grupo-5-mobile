package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetalleDescripcionFichaLibro(
    descripcion: String
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Descripción",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = descripcion,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Light
        )
    }
}