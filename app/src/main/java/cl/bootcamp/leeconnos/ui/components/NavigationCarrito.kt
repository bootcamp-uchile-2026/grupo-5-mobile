package cl.bootcamp.leeconnos.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun NavigationCarrito(
    onNavigateTo: (String) -> Unit
) {

    IconButton(
        onClick = {
            onNavigateTo(SecondaryRoutes.Carrito.route)
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_carrito),
            contentDescription = "Ver carrito",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}