package cl.bootcamp.leeconnos.ui.components.topbar

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun NavigationCarrito(
    cantidadItems: () -> Int,
    onNavigateTo: (String) -> Unit
) {

    BadgedBox(
        badge = {

            val cantidad = cantidadItems()

            if (cantidad > 0) {

                Badge(
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .offset(x = (-6).dp, y = 6.dp )
                ) {

                    val texto = if (cantidad > 99) "99+" else cantidad.toString()

                    Text(
                        text = texto
                    )
                }
            }
        }
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
}