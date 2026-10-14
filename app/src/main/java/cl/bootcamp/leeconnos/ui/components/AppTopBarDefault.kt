package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarDefault(
    cantidadItems: () -> Int,
    onNavigateTo: (String) -> Unit
){
    //Búsqueda + Lista Deseados + Carrito

    CenterAlignedTopAppBar(

        title = {
            //TODO: Aquí el buscador? o en navigation icons
        },

        actions = {
            NavigationListaDeseados(onNavigateTo)
            NavigationCarrito(
                cantidadItems = cantidadItems,
                onNavigateTo = onNavigateTo
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),

        windowInsets = WindowInsets(0.dp)
    )
}