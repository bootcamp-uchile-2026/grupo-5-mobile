package cl.bootcamp.leeconnos.ui.components.desplegables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun DesplegableCompra(
    icono: Int,
    nombre: String,
    resultado: @Composable () -> Unit,
    contenidoDesplegable: @Composable () -> Unit
) {

    var expandido by remember { mutableStateOf(false) }

    // Animación para rotar la flecha
    val rotacionFlecha by animateFloatAsState(
        targetValue = if (expandido) 180f else 0f,
        label = "rotacion_flecha"
    )


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandido = !expandido } // Aquí cambiamos el estado

        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = icono),
                    contentDescription = null
                )

                Text(
                    text = nombre,
                    style = MaterialTheme.typography.labelLarge
                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                resultado()

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "Expandir detalles",
                    modifier = Modifier.rotate(rotacionFlecha) // Aplicamos la rotación aquí
                )
            }
        }


        AnimatedVisibility(visible = expandido) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                contenidoDesplegable()
            }
        }
    }
}