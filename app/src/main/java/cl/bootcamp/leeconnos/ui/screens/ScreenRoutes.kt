package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import cl.bootcamp.leeconnos.R

enum class ScreenRoutes(
    val route: String,
    val title: String,
    val icon: Int,
    val selectedColor: Color? = null,
    val iconSelectedColor: Color? = null
) {
    HOME(
        route = "inicio",
        title = "Inicio",
        icon = R.drawable.ic_home
    ),

    CATALOGO(
        route = "catalogo",
        title = "Catálogo",
        icon = R.drawable.ic_catalogo
    ),

    DESCUBRE(
        route = "descubre",
        title = "Descubre",
        icon = R.drawable.ic_descubre
    ),

    COMUNIDAD(
        route = "comunidad",
        title = "Comunidad",
        icon = R.drawable.ic_comunidad
    ),

    MI_CUENTA(
        route = "mi_cuenta",
        title = "Mi Cuenta",
        icon = R.drawable.ic_perfil
    )
}