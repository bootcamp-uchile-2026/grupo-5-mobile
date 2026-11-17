package cl.bootcamp.leeconnos.ui.components.campostexto

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun CampoTexto(
    texto: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {

    OutlinedTextField(
        value = texto,
        onValueChange = { nuevoValor ->
            onValueChange(nuevoValor)
        },
        label = { Text(text = label, style = MaterialTheme.typography.bodySmall) },
        enabled = enabled,
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        textStyle = MaterialTheme.typography.bodySmall,
        modifier = modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        )
    )
}