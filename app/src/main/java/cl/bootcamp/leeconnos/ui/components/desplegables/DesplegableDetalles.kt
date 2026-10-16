package cl.bootcamp.leeconnos.ui.components.desplegables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun DesplegableDetalles() {

    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Detalles",
            style = MaterialTheme.typography.labelLarge
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "Desplegar"
        )
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
}