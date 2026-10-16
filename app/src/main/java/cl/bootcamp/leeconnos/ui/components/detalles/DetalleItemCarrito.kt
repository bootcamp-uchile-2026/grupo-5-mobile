package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.data.modelos.Libro

@Composable
fun DetalleItemCarrito(
    libro: Libro,
    modifier: Modifier,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {

    Column(
        modifier = modifier
    ) {

        DetalleItemLibro(
            titulo = libro.titulo,
            autor = libro.autor,
            precio = libro.precio,
            tieneOferta = true,
            precioAnterior = "10000"
        )

        SelectorCantidad(
            cantidad = libro.cantidad,
            onIncrease = { onIncrease() },
            onDecrease = { onDecrease() }
        )
    }
}