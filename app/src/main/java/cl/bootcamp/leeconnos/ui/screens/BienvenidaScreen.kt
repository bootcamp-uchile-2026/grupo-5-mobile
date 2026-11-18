package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal

@Composable
fun BienvenidaScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToRegistro: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.imagen_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 64.dp, horizontal = 24.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(.8f)
                    .padding(top = 24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Leeconnos",
                    modifier = Modifier.padding(end = 16.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.nombre_app),
                    contentDescription = "Logo Leeconnos",
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {

                BotonPrincipal(
                    textoBtn = "Iniciar Sesión",
                    onClick = onNavigateToLogin,
                    modifier = Modifier.padding(horizontal = 16.dp),
                    estiloTexto = MaterialTheme.typography.bodyMedium,
                    contentPadding = PaddingValues(vertical = 24.dp)

                )

                Spacer(modifier = Modifier.height(24.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.clickable { onNavigateToRegistro() },
                ) {
                    Text(
                        text = "¿Eres nuevo? ",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = 16.dp)
                    )

                    Text(
                        text = "Regístrate aquí",
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        style = MaterialTheme.typography.bodyMedium,

                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }
}