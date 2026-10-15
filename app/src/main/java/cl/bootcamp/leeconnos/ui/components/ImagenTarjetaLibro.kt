package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ImagenTarjetaLibro(
    imagen: String,
    contentDescription: String,
    aspectRatio: Float = 1.4f
){

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imagen)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
            .clip(
                RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp
                )
            ),

        placeholder = painterResource(id = R.drawable.fondo_transparente),
        error = painterResource(id = R.drawable.fondo_transparente)
    )
}