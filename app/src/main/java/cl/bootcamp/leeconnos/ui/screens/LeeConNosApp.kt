package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import cl.bootcamp.leeconnos.ui.components.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.AppTopBarDefault
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenData
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun LeeConNosApp(
    viewModel: MainViewModel = viewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ScreenRoutes.HOME.route

    val pantallaActual = remember(currentRoute) {
        ScreenRoutes.entries.find { it.route == currentRoute }
            ?: SecondaryRoutes.values.find { it.route == currentRoute }
    }

    Scaffold(

        bottomBar = {

            if (pantallaActual?.showBottomBar ?: false) {
                AppBottomBar(
                    rutaActual = currentRoute,
                    onNavigateTo = { ruta ->

                        navController.navigate(ruta) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        },

        snackbarHost = { },
        modifier = Modifier.fillMaxSize()

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "graph_home",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),

            enterTransition = {

                val rutaOrigen = initialState.destination.route ?: ""
                val rutaDestino = targetState.destination.route ?: ""

                val indiceOrigen = ScreenRoutes.entries.indexOfFirst { it.route == rutaOrigen }
                val indiceDestino = ScreenRoutes.entries.indexOfFirst { it.route == rutaDestino }

                if (indiceOrigen != -1 && indiceDestino != -1) {

                    if (indiceDestino > indiceOrigen) {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300))

                    } else {
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(300))
                    }

                } else {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300))
                }

            },

            exitTransition = {

                val rutaOrigen = initialState.destination.route ?: ""
                val rutaDestino = targetState.destination.route ?: ""

                val indiceOrigen = ScreenRoutes.entries.indexOfFirst { it.route == rutaOrigen }
                val indiceDestino = ScreenRoutes.entries.indexOfFirst { it.route == rutaDestino }

                if (indiceOrigen != -1 && indiceDestino != -1) {
                    if (indiceDestino > indiceOrigen) {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300))

                    } else {
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(300))
                    }

                } else {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300))
                }
            },

            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },

            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {

            navigation(
                startDestination = ScreenRoutes.HOME.route,
                route = "graph_home"
            ) {

                composable(ScreenRoutes.HOME.route) {
                    HomeScreen(
                        onNavigateTo = { ruta ->
                            navController.navigate(ruta)
                        }
                    )
                }
            }

            navigation(
                startDestination = ScreenRoutes.CATALOGO.route,
                route = "graph_catalogo"
            ) {
                composable(ScreenRoutes.CATALOGO.route) {
                    CatalogoScreen(
                        onNavigateTo = { ruta ->
                            navController.navigate(ruta)
                        }
                    )
                }
            }

            navigation(
                startDestination = ScreenRoutes.DESCUBRE.route,
                route = "graph_descubre"
            ) {
                composable(ScreenRoutes.DESCUBRE.route) {
                    DescubreScreen()
                }

                composable(SecondaryRoutes.FichaLibrero.route) {
                    //FichaLibreroScreen
                }
            }

            navigation(
                startDestination = ScreenRoutes.COMUNIDAD.route,
                route = "graph_comunidad"
            ) {
                composable(ScreenRoutes.COMUNIDAD.route) {
                    ComunidadScreen()
                }
            }

            navigation(
                startDestination = ScreenRoutes.MI_CUENTA.route,
                route = "graph_mi_cuenta"
            ) {

                composable(ScreenRoutes.MI_CUENTA.route) {
                    MiCuentaScreen()
                }

                composable(SecondaryRoutes.MiPerfil.route) {
                    //MiPerfilScreen
                }
                composable(SecondaryRoutes.HistorialCompras.route) {
                    //HistorialComprasScreen
                }
                composable(SecondaryRoutes.MiBiblioteca.route) {
                    //MiBibliotecaScreen
                }
                composable(SecondaryRoutes.MisResenas.route) {
                    //MisResenasScreen
                }
                composable(SecondaryRoutes.MisConsultas.route) {
                    //MisConsultasScreen
                }
                composable(SecondaryRoutes.Configuracion.route) {
                    //ConfiguracionScreen
                }
                composable(SecondaryRoutes.Ayuda.route) {
                    //AyudaScreen
                }
            }


            navigation(
                startDestination = SecondaryRoutes.Carrito.route,
                route = "graph_carrito"
            ){

                composable(SecondaryRoutes.Carrito.route) {
                    CarritoScreen(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }

                composable(SecondaryRoutes.ConfirmacionCompra.route) {
//                    CarritoScreen(
//                        onNavigateBack = { navController.popBackStack() }
//                    )
                }
            }

            composable(SecondaryRoutes.ListaDeseados.route) {
                ListaDeseadosScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(SecondaryRoutes.FichaLibro.route) {
                //FichaLibroScreen
            }
        }
    }
}