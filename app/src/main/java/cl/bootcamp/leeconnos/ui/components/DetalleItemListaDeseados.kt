package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.data.modelos.Libro

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
            tieneOferta = true,
            precioAnterior = "10000"
        )
        
        BotonAgregarAlCarrito(
            onBtnClick = {
                onAddClick()
            }
        )
    }
}