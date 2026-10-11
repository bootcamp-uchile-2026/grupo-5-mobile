package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun DescubreScreen(){

    Scaffold(
        topBar = {
            AppTopBarSimple(
                titulo = ScreenRoutes.DESCUBRE.title
            )
        }
    ) { paddingValues ->

        Text(
            text = "Descubre",
            modifier = Modifier.padding(paddingValues)
        )
    }
}