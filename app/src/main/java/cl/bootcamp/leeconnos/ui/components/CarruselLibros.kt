package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CarruselLibros(
    titulo: String
    //lista de libros
){
    Text(
        text = titulo,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(count = 10) {

            TarjetaLibro()
        }
    }
}