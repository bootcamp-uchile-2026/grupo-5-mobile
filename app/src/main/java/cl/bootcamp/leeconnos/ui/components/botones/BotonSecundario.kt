package cl.bootcamp.leeconnos.ui.components.botones

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BotonSecundario(
    textoBtn: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icono: Int? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    textFontWeight: FontWeight = FontWeight.Normal,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {

    OutlinedButton(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.background,
            disabledContentColor = MaterialTheme.colorScheme.outlineVariant
        ),
        onClick = {
            onClick()
        },
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPadding,
        border = BorderStroke(
            width = 1.dp,
            color = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        )
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (icono != null) {
                Icon(
                    painter = painterResource(id = icono),
                    contentDescription = "",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Text(
                text = textoBtn,
                style = textStyle,
                fontWeight = textFontWeight
            )
        }
    }
}