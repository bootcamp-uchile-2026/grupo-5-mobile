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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import cl.bootcamp.leeconnos.ui.components.MensajeError
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.bottombar.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.campostexto.CampoTexto
import cl.bootcamp.leeconnos.ui.components.cards.TituloCardLogin
import cl.bootcamp.leeconnos.ui.components.iconbuttons.BotonAccesoRedes
import cl.bootcamp.leeconnos.ui.components.selectores.ItemCheckbox
import cl.bootcamp.leeconnos.ui.components.selectores.SelectorAutenticacion
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes
import cl.bootcamp.leeconnos.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    tipo: String,
    onNavigateBack: () -> Unit,
    onLoginExitoso: () -> Unit
) {

    val loginViewModel: LoginViewModel = koinViewModel()
    val uiState by loginViewModel.uiState.collectAsState()

    var esLogin by rememberSaveable { mutableStateOf(tipo == "login") }

    LaunchedEffect(uiState.loginExitoso) {
        if (uiState.loginExitoso) {
            onLoginExitoso()
        }
    }

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
                    .heightIn(max = 860.dp)
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
                        onIniciarSesionClick = {
                            if (!esLogin) {
                                esLogin = true
                                loginViewModel.cambiarModoAutenticacion()
                            }
                        },
                        onRegistroClick = {
                            if (esLogin) {
                                esLogin = false
                                loginViewModel.cambiarModoAutenticacion()
                            }
                        },
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
                                texto = uiState.email,
                                label = "Correo electrónico *",
                                onValueChange = { nuevoEmail ->
                                    loginViewModel.onEmailChange(nuevoEmail)
                                },
                                isError = uiState.errorMessage != null,
                                keyboardType = KeyboardType.Text //Por ahora obligado por el problema del recuadro amarillo
                            )

                            CampoTexto(
                                texto = uiState.contrasena,
                                label = "Contraseña *",
                                onValueChange = { nuevaContrasena ->
                                    loginViewModel.onContrasenaChange(nuevaContrasena)
                                },
                                isError = uiState.errorMessage != null,
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

                            MensajeError(
                                mensaje = uiState.errorMessage,
                                modifier = Modifier.fillMaxWidth()
                            )

                            BotonPrincipal(
                                textoBtn = if (uiState.isLoading) "Cargando..." else "Iniciar sesión",
                                onClick = {
                                    loginViewModel.login()
                                },
                                enabled = uiState.esLoginValido && !uiState.isLoading,
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
                                        //TODO: Pendiente implementación como modo invitado sin cuenta
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
                                    onClick = {
                                        //TODO: Pendiente
                                    }
                                )

                                Spacer(modifier = Modifier.width(32.dp))

                                BotonAccesoRedes(
                                    icono = R.drawable.ic_facebook,
                                    contentDescription = "Acceder con cuenta de facebook",
                                    onClick = {
                                        //TODO: Pendiente
                                    }
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
                                texto = uiState.nombre,
                                label = "Nombre *",
                                onValueChange = { nuevoNombre ->
                                    loginViewModel.onNombreChange(nuevoNombre)
                                },
                                //isError = uiState.errorMessage != null
                            )

                            CampoTexto(
                                texto = uiState.apellido,
                                label = "Apellido *",
                                onValueChange = { nuevoApellido ->
                                    loginViewModel.onApellidoChange(nuevoApellido)
                                },
                                //isError = uiState.errorMessage != null
                            )

                            CampoTexto(
                                texto = uiState.email,
                                label = "Correo electrónico *",
                                onValueChange = { nuevoEmail ->
                                    loginViewModel.onEmailChange(nuevoEmail)
                                },
                                isError = uiState.errorMessage != null,
                                keyboardType = KeyboardType.Text
                            )

                            CampoTexto(
                                texto = uiState.contrasena,
                                label = "Contraseña *",
                                onValueChange = { nuevaContrasena ->
                                    loginViewModel.onContrasenaChange(nuevaContrasena)
                                },
                                isError = uiState.errorMessage != null,
                                keyboardType = KeyboardType.Password,
                                esContrasena = true,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            CampoTexto(
                                texto = uiState.repetirContrasena,
                                label = "Repetir contraseña *",
                                onValueChange = { nuevaContrasena ->
                                    loginViewModel.onRepetirContrasenaChange(nuevaContrasena)
                                },
                                isError = uiState.errorMessage != null,
                                keyboardType = KeyboardType.Password,
                                esContrasena = true,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            ItemCheckbox(
                                checked = uiState.aceptoTerminos,
                                onCheckedChange = {
                                    loginViewModel.onAceptoTerminosChange(!uiState.aceptoTerminos)
                                },
                                texto = "He leído y acepto los Términos y condiciones y la Política de privacidad de Leeconnos",
                                modifier = Modifier.padding(bottom = 16.dp),
                                isError = uiState.errorMessage != null
                            )

                            ItemCheckbox(
                                checked = uiState.recibirNovedades,
                                onCheckedChange = {
                                    loginViewModel.onRecibirNovedadesChange(!uiState.recibirNovedades)
                                },
                                texto = "Quiero recibir novedades, recomendaciones de libros y ofertas de Leeconnos por correo",
                                esOpcional = true
                            )

                            MensajeError(
                                mensaje = uiState.errorMessage ?: uiState.mensajeErrorRegistro,
                                modifier = Modifier.fillMaxWidth()
                            )

                            BotonPrincipal(
                                textoBtn = "Crear cuenta",
                                onClick = {
                                    loginViewModel.registrar()
                                },
                                textStyle = MaterialTheme.typography.bodyLarge,
                                textFontWeight = FontWeight.Bold,
                                enabled = uiState.esRegistroValido && !uiState.isLoading,
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