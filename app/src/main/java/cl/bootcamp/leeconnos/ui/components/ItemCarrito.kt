package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun ItemCarrito(
    //objeto libro
    onDeleteClick: () -> Unit
){

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        ImagenItem(
            imagen = "",
            modifier = Modifier.weight(.3f)
        )

        DetalleItemCarrito(
            modifier = Modifier.weight(.6f)
        )

        IconButton(
            onClick = {
                onDeleteClick()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_basurero),
                contentDescription = "Eliminar libro del carrito"
            )
        }
    }
}