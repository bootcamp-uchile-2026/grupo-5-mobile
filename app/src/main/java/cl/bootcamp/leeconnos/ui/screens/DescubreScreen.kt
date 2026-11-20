package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.bottombar.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun DescubreScreen(
    onNavigateBottomBar: (String) -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarSimple(
                titulo = ScreenRoutes.DESCUBRE.title
            )
        },

        bottomBar = {
            AppBottomBar(
                rutaActual = ScreenRoutes.DESCUBRE.route,
                onNavigateTo = onNavigateBottomBar
            )
        },

        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Text(
            text = "Descubre",
            modifier = Modifier.padding(paddingValues)
        )
    }
}