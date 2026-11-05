package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.ui.components.carruseles.CarruselCategorias
import cl.bootcamp.leeconnos.ui.components.desplegables.DesplegableDetalles

@Composable
fun DetalleFichaProducto(
    libro: Libro
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(.9f)
            ) {

                DetalleTituloAutor(
                    titulo = libro.titulo,
                    autor = libro.autor
                )

                CarruselCategorias(
                    listaCategorias = libro.categorias
                )

                Spacer(modifier = Modifier.height(16.dp))

                EstrellasValoracion(
                    valoracion = libro.calificacion.toFloat(),
                    texto = "(${libro.cantidadOpiniones})",
                    modifier = Modifier.padding(end = 4.dp)
                )
            }

            IconButton(
                modifier = Modifier.weight(.1f),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_compartir),
                    contentDescription = "Compartir"
                )
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Text(
                    text = "$${libro.precio}",
                    style = MaterialTheme.typography.titleLarge
                )

                if(libro.enOferta){

                    Text(
                        text = "$${libro.precioAnterior}",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Light,
                        textDecoration = TextDecoration.LineThrough
                    )

                    Text(
                        text = "-${libro.descuentoPorcentaje}% dcto",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            CaracteristicaFichaProducto(
                concepto = "Editorial:",
                definicion = libro.editorial
            )

            CaracteristicaFichaProducto(
                concepto = "Encuadernación:",
                definicion = libro.encuadernacion
            )

            CaracteristicaFichaProducto(
                concepto = "N° Páginas:",
                definicion = "${libro.paginas}"
            )

            CaracteristicaFichaProducto(
                concepto = "Dimensiones:",
                definicion = libro.dimensiones
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        DetalleDescripcionFichaLibro(
            descripcion = libro.descripcion
        )
    }
}