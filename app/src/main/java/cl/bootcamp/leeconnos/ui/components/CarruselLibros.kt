package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.data.modelos.Libro

@Composable
fun CarruselLibros(
    titulo: String,
    libros: List<Libro>,
    onAddClick: (Libro) -> Unit
){
    Text(
        text = titulo,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(libros) { libro ->

            TarjetaLibro(
                libro = libro,
                onAddClick = onAddClick
            )
        }
    }
}