package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.data.modelos.Libro
import cl.bootcamp.leeconnos.ui.components.botones.BotonAgregarAlCarrito

@Composable
fun DetalleItemListaDeseados(
    libro: Libro,
    modifier: Modifier,
    onAddClick: () -> Unit
){
    Column(
        modifier = modifier
    ) {

        DetalleItemLibro(
            titulo = libro.titulo,
            autor = libro.autor,
            precio = libro.precio,
            tieneOferta = libro.enOferta,
            precioAnterior = libro.precioAnterior
        )

        BotonAgregarAlCarrito(
            onBtnClick = {
                onAddClick()
            }
        )
    }
}