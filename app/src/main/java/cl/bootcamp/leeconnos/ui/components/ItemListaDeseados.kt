package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Libro

@Composable
fun ItemListaDeseados(
    libro: Libro,
    onDeleteClick: () -> Unit,
    onAddClick: () -> Unit
){

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        ImagenItem(
            imagen = libro.portadaUrl,
            modifier = Modifier.weight(.3f)
        )

        DetalleItemListaDeseados(
            libro = libro,
            modifier = Modifier.weight(.6f),
            onAddClick = onAddClick,
        )

        IconButton(
            onClick = {
                onDeleteClick()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_corazon_deseados_filled),
                contentDescription = "Eliminar libro de lista deseados",
                tint = Color(0xFFF86767),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}