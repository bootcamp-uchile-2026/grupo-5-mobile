package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun SeccionBienvenida(
    nombre: String
) {

    Column(
        modifier = Modifier
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {

        Text(
            text = "Hola, $nombre",
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            text = "Bienvenido a",
            style = MaterialTheme.typography.labelMedium
        )

        Text(
            text = "Leeconnos",
            style = MaterialTheme.typography.displaySmall
        )

        ImagenBannerHorizontal(
            painterId = R.drawable.banner_bienvenida,
            contentDescription = ""
        )
    }
}