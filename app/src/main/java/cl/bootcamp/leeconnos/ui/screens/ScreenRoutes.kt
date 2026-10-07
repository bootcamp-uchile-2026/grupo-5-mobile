package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenRoutes(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
) {
    HOME(
        route = "inicio",
        title = "Inicio",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),

    CATALOGO(
        route = "catalogo",
        title = "Catálogo",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),

    DESCUBRE(
        route = "descubre",
        title = "Descubre",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),

    COMUNIDAD(
        route = "comunidad",
        title = "Comunidad",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),

    MI_CUENTA(
        route = "mi_cuenta",
        title = "Mi Cuenta",
        selectedIcon = Icons.Filled.Person,
        unSelectedIcon = Icons.Outlined.Person
    )
}