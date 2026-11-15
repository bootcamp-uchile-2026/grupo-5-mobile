package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DetalleSubtotalCompra(
    concepto: String,
    valor: String,
    colorClaro: Boolean = true
){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = concepto,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = if (colorClaro) FontWeight.Light else FontWeight.Normal
        )

        Text(
            text = valor,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = if (colorClaro) FontWeight.Light else FontWeight.Normal
        )
    }
}