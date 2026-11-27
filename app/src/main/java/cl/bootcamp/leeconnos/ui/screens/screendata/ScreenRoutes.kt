package cl.bootcamp.leeconnos.ui.screens.screendata

import androidx.compose.ui.graphics.Color
import cl.bootcamp.leeconnos.R

enum class ScreenRoutes(
    override val route: String,
    override val title: String,
    override val icon: Int
) : ScreenData {
    HOME(
        route = "inicio",
        title = "Inicio",
        icon = R.drawable.ic_home
    ),

    //Ruta = "genero" porque la pantalla catálogo es la que sigue después del filtro de exploración mediante géneros
    CATALOGO(
        route = "genero",
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