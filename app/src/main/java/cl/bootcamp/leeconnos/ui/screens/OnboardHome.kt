package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.bottombar.BottomBarSecundario
import cl.bootcamp.leeconnos.ui.theme.AppSecondaryFontFamily

@Composable
fun OnboardHome(
    onNavigateToHome: () -> Unit = {}
) {

    Scaffold(

        bottomBar = {

            BottomBarSecundario(
                content = {

                    BotonPrincipal(
                        textoBtn = "Comencemos",
                        onClick = onNavigateToHome,
                        iconoDerecha = R.drawable.ic_arrow_onboard,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        textFontWeight = FontWeight.Bold,
                        contentPadding = PaddingValues(vertical = 20.dp)
                    )
                }
            )
        },

        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            Image(
                painter = painterResource(id = R.drawable.onboard_home),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(.7f)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(.3f)
                    .padding(vertical = 24.dp, horizontal = 16.dp)
            ) {

                Text(
                    text = buildAnnotatedString {

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary,
                                fontFamily = AppSecondaryFontFamily,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize
                            )
                        ) {
                            append("Encuentra ese libro")
                        }

                        withStyle(
                            style = SpanStyle(
                                fontFamily = AppSecondaryFontFamily,
                                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                fontWeight = FontWeight.Light
                            )
                        ) {
                            append("\nque aún no conoces")
                        }
                    }
                )

                Text(
                    text = "Descubre tu próxima lectura y conecta con una comunidad de lectores como tú.",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}