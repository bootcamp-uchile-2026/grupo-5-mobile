package cl.bootcamp.leeconnos.ui.components.campostexto

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun CampoTexto(
    texto: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    mensajeError: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    esContrasena: Boolean = false
) {

    var passwordVisible by remember { mutableStateOf(false) }

    //Lógica necesaria para que el cursor no se quede atrás al seleccionar una recomendación de dirección
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = texto)) }

    LaunchedEffect(texto) {
        if (texto != textFieldValue.text) {
            textFieldValue = TextFieldValue(
                text = texto,
                selection = TextRange(texto.length)
            )
        }
    }

    OutlinedTextField(
        value = textFieldValue,
        onValueChange = { nuevoValor ->

            //Nota: No funciona, aún así sigue mostrando el recuadro amarillo, tiempo perdido aquí: 45 minutos
            //Código para arreglar el bug del recuadro amarillo al elegir sugerencias completas del teclado (Xiaomi y Gboard)
            val textoAnterior = textFieldValue.text
            val textoNuevo = nuevoValor.text

            // Si el texto insertado de golpe es mayor a 1 carácter (sugerencia o pegar texto)
            val valorAjustado = if (nuevoValor.composition != null && (textoNuevo.length - textoAnterior.length > 1)) {
                nuevoValor.copy(composition = null)
            } else {
                nuevoValor
            }

            textFieldValue = valorAjustado

            onValueChange(valorAjustado.text)
        },
        label = { Text(text = label, style = MaterialTheme.typography.bodySmall) },
        enabled = enabled,
        isError = isError,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        textStyle = MaterialTheme.typography.bodySmall,
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            autoCorrectEnabled = false,

        ),
        visualTransformation = if (esContrasena && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {

            if (esContrasena) {

                val imagenOjo = if (passwordVisible) {
                    R.drawable.ic_ojo
                } else {
                    R.drawable.ic_ojo_cerrado
                }

                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        painter = painterResource(id = imagenOjo),
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        },
        supportingText = {
            if (isError && mensajeError != null)
                Text(
                    text = mensajeError,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelLarge,
                )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,

            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,

            errorContainerColor = MaterialTheme.colorScheme.background,
            disabledContainerColor = MaterialTheme.colorScheme.background
        )
    )
}