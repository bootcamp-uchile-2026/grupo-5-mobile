package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.AppTopBarDefault
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import kotlinx.coroutines.flow.scan

@Composable
fun CatalogoScreen(
    mainViewModel: MainViewModel,
    onNavigateTo: (String) -> Unit
){

    val itemsCarrito by mainViewModel.itemsCarrito.collectAsState()

    Scaffold(
        topBar = {
            AppTopBarDefault(
                cantidadItems = { itemsCarrito.size },
                onNavigateTo = onNavigateTo
            )
        }
    ) { paddingValues ->

        Text("Catalogo", modifier = Modifier.padding(paddingValues))

    }

}