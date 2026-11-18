package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.leeconnos.ui.components.bottombar.AppBottomBar
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import cl.bootcamp.leeconnos.viewmodel.CheckoutViewModel
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun LeeconnosApp(
) {

    val navController = rememberNavController()

    val onNavigateBottomBar: (String) -> Unit = { rutaDestino ->

        navController.navigate(rutaDestino) {

            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }
    }


    //Implementación necesaria aquí para poder usar el mismo viewmodel entre pantallas y no creen una por su cuenta
    val mainViewModel: MainViewModel = koinViewModel()
    val checkoutViewModel: CheckoutViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = "graph_home",
        modifier = Modifier
            .fillMaxSize(),

        enterTransition = {

            val rutaOrigen = initialState.destination.route ?: ""
            val rutaDestino = targetState.destination.route ?: ""

            val indiceOrigen = ScreenRoutes.entries.indexOfFirst { it.route == rutaOrigen }
            val indiceDestino = ScreenRoutes.entries.indexOfFirst { it.route == rutaDestino }

            if (indiceOrigen != -1 && indiceDestino != -1) {

                if (indiceDestino > indiceOrigen) {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(300)
                    )

                } else {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(300)
                    )
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
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        tween(300)
                    )

                } else {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        tween(300)
                    )
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
            startDestination = SecondaryRoutes.BienvenidaScreen.route,
            route = "graph_login"
        ) {

            composable(SecondaryRoutes.BienvenidaScreen.route) {
                BienvenidaScreen(
                    onNavigateToLogin = {
                        navController.navigate(SecondaryRoutes.LoginScreen.route + "/login")
                    },
                    onNavigateToRegistro = {
                        navController.navigate(SecondaryRoutes.LoginScreen.route + "/registro")
                    }
                )
            }

            composable(
                route = SecondaryRoutes.LoginScreen.route + "/{tipoAuth}",
                arguments = listOf(navArgument("tipoAuth") { type = NavType.StringType })
            ) { backStackEntry ->

                val tipoAuth = backStackEntry.arguments?.getString("tipoAuth") ?: "login"

                LoginScreen(tipo = tipoAuth)
            }
        }

        navigation(
            startDestination = ScreenRoutes.HOME.route,
            route = "graph_home"
        ) {

            composable(ScreenRoutes.HOME.route) {
                HomeScreen(
                    mainViewModel = mainViewModel,
                    onNavigateBottomBar = onNavigateBottomBar,
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
                    mainViewModel = mainViewModel,
                    onNavigateBottomBar = onNavigateBottomBar,
                    onNavigateTo = { ruta ->
                        navController.navigate(ruta)
                    }
                )
            }
        }


        navigation(
            startDestination = SecondaryRoutes.Carrito.route,
            route = "graph_carrito"
        ) {

            composable(SecondaryRoutes.Carrito.route) {
                CarritoScreen(
                    mainViewModel = mainViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateTo = { ruta ->
                        navController.navigate(ruta)
                    }
                )
            }
        }

        composable(SecondaryRoutes.ListaDeseados.route) {
            ListaDeseadosScreen(
                mainViewModel = mainViewModel,
                onNavigateBack = { navController.popBackStack() },
                onNavigateTo = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }

        navigation(
            startDestination = SecondaryRoutes.FichaLibro.route,
            route = "graph_route"
        ) {

            composable(SecondaryRoutes.FichaLibro.route) {
                FichaLibroScreen(
                    mainViewModel = mainViewModel,
                    onNavigateTo = { ruta ->
                        navController.navigate(ruta)
                    },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(SecondaryRoutes.Resenas.route) {

            }
        }


        navigation(
            startDestination = SecondaryRoutes.ConfirmacionCompra.route,
            route = "graph_checkout"
        ) {


            composable(SecondaryRoutes.ConfirmacionCompra.route) {

                ConfirmacionCompraScreen(
                    checkoutViewModel = checkoutViewModel,
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateTo = { ruta ->
                        navController.navigate(ruta)
                    },
                    onPagarClick = {}
                )
            }

            composable(SecondaryRoutes.AgregarDireccion.route) {
                AgregarDireccionScreen(
                    checkoutViewModel = checkoutViewModel,
                    onNavigateBack = { navController.popBackStack() },
                )
            }

            composable(SecondaryRoutes.AgregarTarjeta.route) {
                AgregarTarjetaScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onAgregarClick = {}
                )
            }
        }


        //Pendientes sin Wireframe
        navigation(
            startDestination = ScreenRoutes.DESCUBRE.route,
            route = "graph_descubre"
        ) {
            composable(ScreenRoutes.DESCUBRE.route) {
                DescubreScreen(
                    onNavigateBottomBar = onNavigateBottomBar,
                )
            }

            composable(SecondaryRoutes.FichaLibrero.route) {
                //Ficha librero
            }
        }

        navigation(
            startDestination = ScreenRoutes.COMUNIDAD.route,
            route = "graph_comunidad"
        ) {
            composable(ScreenRoutes.COMUNIDAD.route) {
                ComunidadScreen(
                    onNavigateBottomBar = onNavigateBottomBar,
                )
            }
        }

        navigation(
            startDestination = ScreenRoutes.MI_CUENTA.route,
            route = "graph_mi_cuenta"
        ) {

            composable(ScreenRoutes.MI_CUENTA.route) {
                MiCuentaScreen(
                    mainViewModel = mainViewModel,
                    onNavigateBottomBar = onNavigateBottomBar,
                )
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
    }
}
