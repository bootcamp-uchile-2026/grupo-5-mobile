package cl.bootcamp.leeconnos.ui.components.detalles

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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

                //Estrellas
                ValoracionFichaLibro(
                    valoracion = libro.calificacion.toFloat(),
                    opiniones = libro.cantidadOpiniones
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

        HorizontalDivider(modifier = Modifier.padding(top = 8.dp, bottom = 16.dp))

        DetalleDescripcionFichaLibro(
            descripcion = libro.descripcion
        )

        //Desplegable detalles
        DesplegableDetalles()
    }
}