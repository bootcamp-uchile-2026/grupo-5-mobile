package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.R

@Composable
fun ImagenItem(
    imagen: String,
    modifier: Modifier
) {

    Image(
        painter = painterResource(id = R.drawable.fondo_transparente),
        contentDescription = "Foto del libro",
        contentScale = ContentScale.FillBounds,
        modifier = modifier
            //.weight(.3f)
            .clip(shape = MaterialTheme.shapes.medium)
            .aspectRatio(0.7f)
    )
}