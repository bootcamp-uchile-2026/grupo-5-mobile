package cl.bootcamp.leeconnos.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AlertaConfirmacion(
    titulo: String?,
    pregunta: String,
    textoBtnConfirmar: String,
    textoBtnCancelar: String,
    onCancelar: () -> Unit,
    onConfirmar: () -> Unit
) {

    AlertDialog(

        containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        iconContentColor = MaterialTheme.colorScheme.secondary,
        titleContentColor = MaterialTheme.colorScheme.onSurface,
        textContentColor = MaterialTheme.colorScheme.onSurfaceVariant,

        onDismissRequest = {
            onCancelar()
        },
        title = {
            Text(
                text = titulo ?: "",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        text = {
            Text(
                text = pregunta,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmar()
                }
            ) {
                Text(
                    text = textoBtnConfirmar,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancelar()
                }
            ) {
                Text(
                    text = textoBtnCancelar,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}