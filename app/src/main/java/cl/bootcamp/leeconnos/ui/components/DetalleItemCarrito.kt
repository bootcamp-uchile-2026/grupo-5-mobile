package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetalleItemCarrito(
    //objeto libro
    modifier: Modifier
) {

    Column(
        modifier = modifier
    ) {

        DetalleItemLibro(
            titulo = "El Perfume",
            autor = "Patrick Süskind",
            precio = "18.830",
            tieneOferta = true,
            precioAnterior = "26.900"
        )

        SelectorCantidad(
            onDecrease = {},
            onIncrease = {}
        )
    }
}