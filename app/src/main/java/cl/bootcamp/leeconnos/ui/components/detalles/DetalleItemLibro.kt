package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun DetalleItemLibro(
    titulo: String,
    autor: String,
    precio: Int,
    tieneOferta: Boolean,
    precioAnterior: String?
){

    Text(
        text = titulo,
        style = MaterialTheme.typography.bodySmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Text(
        text = autor,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.Light,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ){
        Text(
            text = "$ $precio",
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "$ $precioAnterior",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Light,
            textDecoration = TextDecoration.LineThrough
        )
    }
}