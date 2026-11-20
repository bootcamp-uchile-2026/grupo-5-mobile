package cl.bootcamp.leeconnos.ui.components.selectores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun ItemCheckbox(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    texto: String,
    modifier: Modifier = Modifier,
    esOpcional: Boolean = false,
    isError: Boolean = false
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange() }
        )

        //Se crea con buildAnnotatedString para poder unir textos con estilos distintos
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) {
                    append(texto)
                }

                if (esOpcional) {
                    append(" ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append("(opcional)")
                    }
                }
            },
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start = 8.dp),
            color = if (!isError) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.error
        )
    }
}