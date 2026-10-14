package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DetalleItemListaDeseados(
    modifier: Modifier,
    onAddClick: () -> Unit
){
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
        
        BotonAgregarAlCarrito(
            onBtnClick = {
                onAddClick()
            }
        )
    }
}