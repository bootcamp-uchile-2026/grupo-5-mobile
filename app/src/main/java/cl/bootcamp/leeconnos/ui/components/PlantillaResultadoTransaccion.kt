package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.botones.BotonSecundario
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun PlantillaResultadoTransaccion(
    icono: Int,
    titulo: String,
    subtitulo: String,
    textoBtnSecundario: String,
    onBtnSecundarioClick: () -> Unit,
    modifier: Modifier
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(.8f)
        ) {

            Image(
                painter = painterResource(id = icono),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = titulo,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Text(
                text = subtitulo,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 48.dp)
            )

            BotonSecundario(
                textoBtn = textoBtnSecundario,
                onClick = onBtnSecundarioClick
            )
        }
    }
}