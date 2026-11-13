package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun Buscador(
    valor: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    onBuscarClick: () -> Unit,
    modifier: Modifier
) {

    OutlinedTextField(
        value = valor,
        onValueChange = { nuevoValor ->
            onValueChange(nuevoValor)
        },
        placeholder = { Text(placeholder, style = MaterialTheme.typography.bodyMedium) },
        trailingIcon = {

            IconButton(
                onClick = {
                    onBuscarClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_busqueda),
                    contentDescription = "Buscar",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        singleLine = true,
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
        )
    )
}