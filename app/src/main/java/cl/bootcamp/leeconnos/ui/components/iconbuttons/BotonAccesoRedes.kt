package cl.bootcamp.leeconnos.ui.components.iconbuttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun BotonAccesoRedes(
    icono: Int,
    contentDescription: String,
    onClick: () -> Unit
) {

    IconButton(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Image(
            painter = painterResource(id = icono),
            contentDescription = contentDescription,
            modifier = Modifier.padding(8.dp)
        )
    }
}