package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun ComunidadScreen(){

    Scaffold(
        topBar = {
            AppTopBarSimple(
                titulo = ScreenRoutes.COMUNIDAD.title
            )
        }
    ) { paddingValues ->

        Text(
            text = "Comunidad",
            modifier = Modifier.padding(paddingValues)
        )
    }
}