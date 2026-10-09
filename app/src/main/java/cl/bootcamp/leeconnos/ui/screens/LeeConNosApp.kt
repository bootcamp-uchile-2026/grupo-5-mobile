package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.leeconnos.ui.components.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.AppTopBar
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun LeeConNosApp(
    viewModel: MainViewModel = viewModel()
){
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val rutaActual = navBackStackEntry?.destination?.route ?: ScreenRoutes.HOME.route
    val pantallaActual = ScreenRoutes.entries.find { it.route == rutaActual } ?: ScreenRoutes.HOME


    //Refactorizar toda esta lógica
    val esPantallaPrincipal = ScreenRoutes.entries.any { it.route == rutaActual }

    val rutasFlotantes = listOf(
        SecondaryRoutes.CARRITO,
        SecondaryRoutes.LISTA_DESEADOS,
        SecondaryRoutes.FICHA_LIBRO,
        SecondaryRoutes.FICHA_LIBRERO,
        SecondaryRoutes.MI_PERFIL,
        SecondaryRoutes.HISTORIAL_COMPRAS,
        SecondaryRoutes.MI_BIBLIOTECA,
        SecondaryRoutes.MIS_RESENAS,
        SecondaryRoutes.MIS_CONSULTAS,
        SecondaryRoutes.CONFIGURACION,
        SecondaryRoutes.AYUDA
    )

    val titulo = when(rutaActual) {
        SecondaryRoutes.CARRITO -> "Carrito"
        SecondaryRoutes.LISTA_DESEADOS -> "Lista de deseados"
        SecondaryRoutes.MI_PERFIL -> "Mi perfil"
        SecondaryRoutes.CONFIGURACION -> "Configuración"
        SecondaryRoutes.AYUDA -> "Ayuda"

        else -> pantallaActual.title
    }

    val rutasTopBar = listOf(
        SecondaryRoutes.LISTA_DESEADOS,
        SecondaryRoutes.CARRITO
    )

    Scaffold(

        topBar = {
            AppTopBar(titulo = "")
        },

        bottomBar = {
            AppBottomBar(
                rutaActual = rutaActual
            )
        },

        snackbarHost = { },
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = innerPadding)
        ){

            NavHost(
                navController = navController,
                startDestination = "graph_home"
            ) {

                navigation(
                    startDestination = ScreenRoutes.HOME.route,
                    route = "graph_home"
                ) {

                    composable(ScreenRoutes.HOME.route) {
                        HomeScreen()
                    }
                }

                navigation(
                    startDestination = ScreenRoutes.CATALOGO.route,
                    route = "graph_catalogo"
                ) {
                    //CatalogoScreen
                }

                navigation(
                    startDestination = ScreenRoutes.DESCUBRE.route,
                    route = "graph_descubre"
                ) {
                    //DescubreScreen
                    //FichaLibreroScreen
                }

                navigation(
                    startDestination = ScreenRoutes.COMUNIDAD.route,
                    route = "graph_comunidad"
                ) {
                    //ComunidadScreen
                }

                navigation(
                    startDestination = ScreenRoutes.MI_CUENTA.route,
                    route = "graph_mi_cuenta"
                ) {
                    //MiCuentaScreen
                    //MiPerfilScreen
                    //HistorialComprasScreen
                    //MiBibliotecaScreen
                    //MisResenasScreen
                    //MisConsultasScreen
                    //ConfiguracionScreen
                    //AyudaScreen
                }


                composable(SecondaryRoutes.CARRITO) {
                    //CarritoScreen
                }

                composable(SecondaryRoutes.LISTA_DESEADOS) {
                    //ListaDeseadosScreen
                }


                composable(SecondaryRoutes.FICHA_LIBRO) {
                    //FichaLibroScreen
                }
            }
        }
    }
}