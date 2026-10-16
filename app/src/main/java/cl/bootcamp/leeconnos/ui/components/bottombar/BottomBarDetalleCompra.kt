package cl.bootcamp.leeconnos.ui.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonComprarCarrito
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleCarritoAhorro
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleCarritoTotal

@Composable
fun BottomBarDetalleCompra(){

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {

        DetalleCarritoAhorro(
            ahorro = "5.000"
        )

        DetalleCarritoTotal(
            total = "32.830"
        )

        BotonComprarCarrito(
            onComprarClick = {}
        )
    }
}