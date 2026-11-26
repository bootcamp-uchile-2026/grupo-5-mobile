package cl.bootcamp.leeconnos.ui.screens.screendata

import cl.bootcamp.leeconnos.R

sealed class SecondaryRoutes(
    override val route: String,
    override val title: String,
    override val icon: Int? = null
) : ScreenData {

    //LOGIN
    object BienvenidaScreen : SecondaryRoutes(
        route = "bienvenida",
        title = "Bienvenida"
    )

    object LoginScreen : SecondaryRoutes(
        route = "login",
        title = "Iniciar Sesión"
    )

    object OnboardHome : SecondaryRoutes(
        route = "onboard_home",
        title = "Onboard Home"
    )

    //TOP BAR [Flotantes]
    object Carrito : SecondaryRoutes(
        route = "carrito",
        title = "Mi carrito",
        icon = R.drawable.ic_carrito
    )

    object ListaDeseados : SecondaryRoutes(
        route = "lista_deseados",
        title = "Lista de deseados",
        icon = R.drawable.ic_corazon_deseados
    )

    // CATALOGO [Flotante]
    object FichaLibro : SecondaryRoutes(
        route = "ficha_libro",
        title = "Ficha del Libro"
    )

    // RESEÑAS [Anidada de FichaLibro]
    object Resenas : SecondaryRoutes(
        route = "resenas",
        title = "Reseñas"
    )

    // CHECKOUT [Anidada de Carrito]
    object ConfirmacionCompra : SecondaryRoutes(
        route = "confirmacion_compra",
        title = "Confirmación compra"
    )

    object AgregarDireccion : SecondaryRoutes(
        route = "agregar_direccion",
        title = "Elige la dirección de entrega"
    )

    object AgregarTarjeta : SecondaryRoutes(
        route = "agregar_tarjeta",
        title = "Ingresar método de pago"
    )

    object ResultadoTransaccion : SecondaryRoutes(
        route = "resultado_transaccion",
        title = ""
    )

    // PENDIENTES (sin Wireframes)

    object FichaLibrero : SecondaryRoutes(
        route = "ficha_librero",
        title = "Perfil del Librero"
    )

    object MiPerfil : SecondaryRoutes(
        route = "mi_perfil",
        title = "Mi Perfil"
    )

    object HistorialCompras : SecondaryRoutes(
        route = "historial_compras",
        title = "Historial de Compras"
    )

    object MiBiblioteca : SecondaryRoutes(
        route = "mi_biblioteca",
        title = "Mi Biblioteca"
    )

    object MisResenas : SecondaryRoutes(
        route = "mis_resenas",
        title = "Mis Reseñas"
    )

    object MisConsultas : SecondaryRoutes(
        route = "mis_consultas",
        title = "Mis Consultas"
    )

    object Configuracion : SecondaryRoutes(
        route = "configuracion",
        title = "Configuración"
    )

    object Ayuda : SecondaryRoutes(
        route = "ayuda",
        title = "Ayuda"
    )

//    companion object {
//        fun getValues(): List<SecondaryRoutes> = listOf(
//            BienvenidaScreen,
//            LoginScreen,
//            Carrito,
//            ConfirmacionCompra,
//            AgregarDireccion,
//            AgregarTarjeta,
//            ResultadoTransaccion,
//            ListaDeseados,
//            FichaLibro,
//            Resenas,
//            FichaLibrero,
//            MiPerfil,
//            HistorialCompras,
//            MiBiblioteca,
//            MisResenas,
//            MisConsultas,
//            Configuracion,
//            Ayuda
//        )
//    }
}