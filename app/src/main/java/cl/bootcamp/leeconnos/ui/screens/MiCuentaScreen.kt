package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes

@Composable
fun MiCuentaScreen(
){

    Scaffold(
        topBar = {
            AppTopBarSimple(
                titulo = ScreenRoutes.MI_CUENTA.title
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}