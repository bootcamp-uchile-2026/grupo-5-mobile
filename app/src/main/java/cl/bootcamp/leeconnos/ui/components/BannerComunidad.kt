package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.theme.AppTitleFontFamily

@Composable
fun BannerComunidad() {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(.55f)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = "Únete a nuestra comunidad lectora",
                fontFamily = AppTitleFontFamily,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Comparte reseñas y descubre recomendaciones de la comunidad",
                style = MaterialTheme.typography.labelMedium
            )
        }

        Image(
            painter = painterResource(id = R.drawable.fondo_transparente),
            contentDescription = "Foto de comunidad",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(.45f)
                .aspectRatio(1f)
        )
    }
}