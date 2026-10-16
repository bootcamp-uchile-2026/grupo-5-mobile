package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun SelectorCantidad(
    cantidad: Int,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onBackground,
                shape = MaterialTheme.shapes.extraSmall
            )
    ) {

        IconButton(
            onClick = {
                onDecrease()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "Disminuir cantidad",
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = "$cantidad",
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Light
        )

        IconButton(
            onClick = {
                onIncrease()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = "Aumentar cantidad",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}