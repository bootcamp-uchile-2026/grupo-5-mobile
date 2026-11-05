package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarDefault
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun CatalogoScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarDefault(
                cantidadItems = { mainViewModel.obtenerCantidadCarrito() },
                onNavigateTo = onNavigateTo
            )
        }
    ) { paddingValues ->

        Text("Catalogo", modifier = Modifier.padding(paddingValues))

    }

}