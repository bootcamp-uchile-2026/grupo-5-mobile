package cl.bootcamp.leeconnos.ui.screens.screendata

import androidx.compose.ui.graphics.Color
import cl.bootcamp.leeconnos.R

enum class ScreenRoutes(
    override val route: String,
    override val title: String,
    override val icon: Int,
    val selectedColor: Color? = null,
    val iconSelectedColor: Color? = null,
    override val showGlobalTopBar: Boolean = true,
    override val showBottomBar: Boolean = true
) : ScreenData {
    HOME(
        route = "inicio",
        title = "Inicio",
        icon = R.drawable.ic_home,
        showGlobalTopBar = true
    ),

    CATALOGO(
        route = "catalogo",
        title = "Catálogo",
        icon = R.drawable.ic_catalogo,
        showGlobalTopBar = true
    ),

    DESCUBRE(
        route = "descubre",
        title = "Descubre",
        icon = R.drawable.ic_descubre,
        showGlobalTopBar = false
    ),

    COMUNIDAD(
        route = "comunidad",
        title = "Comunidad",
        icon = R.drawable.ic_comunidad,
        showGlobalTopBar = false
    ),

    MI_CUENTA(
        route = "mi_cuenta",
        title = "Mi Cuenta",
        icon = R.drawable.ic_perfil,
        showGlobalTopBar = false
    )
}