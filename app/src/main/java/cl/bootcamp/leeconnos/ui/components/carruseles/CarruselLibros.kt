package cl.bootcamp.leeconnos.ui.components.carruseles

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
import cl.bootcamp.leeconnos.ui.components.cards.TarjetaLibro

@Composable
fun CarruselLibros(
    titulo: String,
    libros: List<Libro>,
    itemsDeseados: List<Libro>,
    onCardClick: (Libro) -> Unit,
    onDeseadosClick: (Libro, Boolean) -> Unit
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

            val esDeseado = itemsDeseados.any { it.id == libro.id }

            val libroActualizado = libro.copy(estaEnListaDeseados = esDeseado)

            TarjetaLibro(
                libro = libroActualizado,
                onCardClick = {
                    onCardClick(libro)
                },
                onDeseadosClick = {

                    onDeseadosClick(libro, esDeseado)
                }
            )
        }
    }
}