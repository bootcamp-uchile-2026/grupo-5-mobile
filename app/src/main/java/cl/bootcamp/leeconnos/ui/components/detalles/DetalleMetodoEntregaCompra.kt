package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun DetalleMetodoEntregaCompra(
    deliverySelected: Boolean,
    retiroSelected: Boolean,
    deliveryOnClick: () -> Unit,
    retiroOnClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        ItemMetodoEntrega(
            icono = R.drawable.ic_truck,
            texto = "Delivery",
            selected = deliverySelected,
            onClick = deliveryOnClick
        )

        ItemMetodoEntrega(
            icono = R.drawable.ic_shopping_bag,
            texto = "Retiro en tienda",
            selected = retiroSelected,
            onClick = retiroOnClick
        )
    }
}