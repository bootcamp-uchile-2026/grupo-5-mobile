package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.ui.components.imagenes.ImagenResumenCompra

@Composable
fun DetalleResumenCompra(
    listaLibros: List<Libro>
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {

        listaLibros.forEach { libro ->

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                ImagenResumenCompra(
                    imagen = libro.portadaUrl,
                    modifier = Modifier.weight(.15f)
                )

                Text(
                    text = "${libro.cantidad}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(.10f)
                )

                Text(
                    text = "${libro.titulo} / ${libro.autor}",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.weight(.50f)
                )

                Text(
                    text = "$${libro.precio}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(.25f)
                )
            }
        }
    }
}