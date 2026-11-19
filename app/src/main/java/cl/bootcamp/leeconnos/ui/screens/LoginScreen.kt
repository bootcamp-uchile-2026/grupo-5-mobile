package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.bottombar.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.campostexto.CampoTexto
import cl.bootcamp.leeconnos.ui.components.cards.TituloCardLogin
import cl.bootcamp.leeconnos.ui.components.iconbuttons.BotonAccesoRedes
import cl.bootcamp.leeconnos.ui.components.selectores.ItemCheckbox
import cl.bootcamp.leeconnos.ui.components.selectores.SelectorAutenticacion
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun LoginScreen(
    tipo: String,
    onNavigateBack: () -> Unit
) {


    var esLogin by rememberSaveable { mutableStateOf(tipo == "login") }

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagen_login),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface
                ),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 830.dp)
                    .padding(paddingValues)
                    .padding(vertical = 24.dp, horizontal = 24.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                ) {

                    TituloCardLogin(
                        onCloseClick = onNavigateBack
                    )

                    SelectorAutenticacion(
                        esLogin = esLogin,
                        onIniciarSesionClick = { esLogin = true },
                        onRegistroClick = { esLogin = false },
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                    )

                    //Formulario
                    if (esLogin) {

                        //Iniciar Sesión

                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp)
                        ) {

                            CampoTexto(
                                texto = "",
                                label = "Correo electrónico *",
                                onValueChange = { nuevoValor ->

                                },
                                isError = false,
                                mensajeError = "Correo electrónico no es válido"
                            )

                            CampoTexto(
                                texto = "",
                                label = "Contraseña *",
                                onValueChange = { nuevoValor ->

                                },
                                isError = false,
                                keyboardType = KeyboardType.Password,
                                esContrasena = true,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            Text(
                                text = "¿Olvidaste tu contraseña?",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.inverseSurface,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .clickable {

                                    }
                            )

                            BotonPrincipal(
                                textoBtn = "Iniciar sesión",
                                onClick = {},
                                textStyle = MaterialTheme.typography.bodyLarge,
                                textFontWeight = FontWeight.Bold,
                                contentPadding = PaddingValues(vertical = 20.dp),
                                modifier = Modifier.padding(vertical = 16.dp)
                            )

                            Text(
                                text = "¿Aún no tienes cuenta?",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.inverseSurface,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )

                            Text(
                                text = "Ingresar como invitado",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .padding(bottom = 16.dp)
                                    .clickable {

                                    }
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                HorizontalDivider( modifier = Modifier.weight(.25f) )

                                Text(
                                    text = "O continúa con",
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Light,
                                    modifier = Modifier
                                        .weight(.5f)
                                        .padding(horizontal = 8.dp),
                                    textAlign = TextAlign.Center
                                )

                                HorizontalDivider( modifier = Modifier.weight(.25f) )
                            }


                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp)
                            ) {

                                BotonAccesoRedes(
                                    icono = R.drawable.ic_google,
                                    contentDescription = "Acceder con cuenta de google",
                                    onClick = {}
                                )

                                Spacer(modifier = Modifier.width(32.dp))

                                BotonAccesoRedes(
                                    icono = R.drawable.ic_facebook,
                                    contentDescription = "Acceder con cuenta de facebook",
                                    onClick = {}
                                )
                            }
                        }

                    } else {

                        //Crear una cuenta
                        Column(
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .verticalScroll(rememberScrollState())
                        ) {

                            CampoTexto(
                                texto = "",
                                label = "Nombre *",
                                onValueChange = {},
                            )

                            CampoTexto(
                                texto = "",
                                label = "Apellido *",
                                onValueChange = {}
                            )

                            CampoTexto(
                                texto = "",
                                label = "Correo electrónico *",
                                onValueChange = {},
                                isError = false,
                                mensajeError = "Correo electrónico no es válido"
                            )

                            CampoTexto(
                                texto = "",
                                label = "Contraseña *",
                                onValueChange = {},
                                isError = false,
                                keyboardType = KeyboardType.Password,
                                esContrasena = true
                            )

                            CampoTexto(
                                texto = "",
                                label = "Repetir contraseña *",
                                onValueChange = {},
                                isError = false,
                                mensajeError = "Contraseña no coincide",
                                keyboardType = KeyboardType.Password,
                                esContrasena = true
                            )

                            ItemCheckbox(
                                checked = false,
                                onCheckedChange = {  },
                                texto = "He leído y acepto los Términos y condiciones y la Política de privacidad de Leeconnos",
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            ItemCheckbox(
                                checked = false,
                                onCheckedChange = {  },
                                texto = "Quiero recibir novedades, recomendaciones de libros y ofertas de Leeconnos por correo",
                                esOpcional = true
                            )

                            BotonPrincipal(
                                textoBtn = "Crear cuenta",
                                onClick = {},
                                textStyle = MaterialTheme.typography.bodyLarge,
                                textFontWeight = FontWeight.Bold,
                                contentPadding = PaddingValues(vertical = 20.dp),
                                modifier = Modifier.padding(vertical = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}