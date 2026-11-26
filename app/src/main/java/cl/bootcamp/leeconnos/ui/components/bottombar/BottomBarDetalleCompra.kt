package cl.bootcamp.leeconnos.ui.components.bottombar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleSubtotalCompra
import cl.bootcamp.leeconnos.ui.components.detalles.DetalleTotalCompra
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun BottomBarDetalleCarrito(
    textoBtn: String,
    ahorro: Int,
    total: Int,
    puedeNavegar: Boolean,
    onNavigateToCheckout: (String) -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {

        DetalleSubtotalCompra(
            concepto = "Ahorro",
            valor = "$$ahorro"
        )

        DetalleTotalCompra(
            concepto = "Total a pagar",
            valor = "$$total"
        )

        BotonPrincipal(
            textoBtn = textoBtn,
            iconoIzquierda = R.drawable.ic_carrito,
            enabled = puedeNavegar,
            onClick = {
                onNavigateToCheckout(SecondaryRoutes.ConfirmacionCompra.route)
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            contentPadding = PaddingValues(vertical = 12.dp)
        )
    }

}