package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.bottombar.AppBottomBar
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun LoginScreen(
    tipo: String
) {

    val esRegistro = tipo == "registro"

    Scaffold(
        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
        ) { }
    }
}