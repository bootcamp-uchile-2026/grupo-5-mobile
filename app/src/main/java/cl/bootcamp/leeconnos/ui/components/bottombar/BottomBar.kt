package cl.bootcamp.leeconnos.ui.components.bottombar

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun AppBottomBar(
    rutaActual: String,
    onNavigateTo: (String) -> Unit
) {

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {

        ScreenRoutes.entries.forEach { pantalla ->

            val estaSeleccionada = rutaActual == pantalla.route

            NavigationBarItem(
                selected = estaSeleccionada,
                label = { Text(text = pantalla.title, style = MaterialTheme.typography.labelSmall) },
                colors = NavigationBarItemColors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    selectedIndicatorColor = MaterialTheme.colorScheme.onSurface,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = pantalla.icon),
                        contentDescription = pantalla.title
                    )
                },
                onClick = {
                    if (rutaActual != pantalla.route) {
                        onNavigateTo(pantalla.route)
                    }
                }
            )
        }
    }
}