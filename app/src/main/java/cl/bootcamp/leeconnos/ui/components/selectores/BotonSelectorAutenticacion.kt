package cl.bootcamp.leeconnos.ui.components.selectores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun BotonSelectorAutenticacion(
    textoBtn: String,
    activo: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {

    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = if (activo) MaterialTheme.colorScheme.background else Color.Transparent,
        border = if (activo) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
        modifier = modifier
            .clickable {
                onClick()
            }
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = textoBtn,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = if (activo) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )
        }
    }
}