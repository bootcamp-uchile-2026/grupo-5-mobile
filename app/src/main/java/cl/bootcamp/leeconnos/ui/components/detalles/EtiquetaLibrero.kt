package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun EtiquetaLibrero(
    nombre: String,
    foto: Int,
    modifier: Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            //.align(Alignment.TopStart) viene en la llamada del padre
            .offset(x = 4.dp, y = 4.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Image(
            painter = painterResource(id = foto),
            contentDescription = "Foto del librero",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(24.dp)
                .clip(MaterialTheme.shapes.extraLarge)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = nombre,
            style = MaterialTheme.typography.labelSmall
        )
    }
}