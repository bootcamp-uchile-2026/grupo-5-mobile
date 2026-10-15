package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImagenFichaLibro(
    imagen: String,
    contentDescription: String
) {

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagen)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),

        placeholder = painterResource(id = R.drawable.fondo_transparente),
        error = painterResource(id = R.drawable.fondo_transparente)
    )
}