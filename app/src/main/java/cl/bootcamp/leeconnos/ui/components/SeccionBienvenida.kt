package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.theme.AppTitleFontFamily

@Composable
fun SeccionBienvenida(
    nombre: String
) {

    Column(
        modifier = Modifier.padding(vertical = 16.dp)
    ) {

        Text(
            text = "Hola, $nombre",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Bienvenido a",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Leeconnos",
            style = MaterialTheme.typography.titleMedium,
            fontFamily = AppTitleFontFamily
        )
    }
}