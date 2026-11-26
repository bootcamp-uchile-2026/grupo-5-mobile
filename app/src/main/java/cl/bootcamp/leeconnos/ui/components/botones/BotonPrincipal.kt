package cl.bootcamp.leeconnos.ui.components.botones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BotonPrincipal(
    textoBtn: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconoIzquierda: Int? = null,
    iconoDerecha: Int? = null,
    enabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    textFontWeight: FontWeight = FontWeight.Normal,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
){

    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            disabledContentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        onClick = {
            onClick()
        },
        enabled = enabled,
        modifier = modifier,
        contentPadding = contentPadding
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            if (iconoIzquierda != null) {
                Icon(
                    painter = painterResource(id = iconoIzquierda),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Text(
                text = textoBtn,
                style = textStyle,
                fontWeight = textFontWeight
            )

            if (iconoDerecha != null) {
                Icon(
                    painter = painterResource(id = iconoDerecha),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
    }
}