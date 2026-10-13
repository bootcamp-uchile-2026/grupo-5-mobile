package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DetalleCarritoAhorro(
    ahorro: String
){

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Ahorro:",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Light
        )

        Text(
            text = "-$$ahorro",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Light
        )
    }
}