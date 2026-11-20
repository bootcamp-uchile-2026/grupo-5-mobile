package cl.bootcamp.leeconnos.ui.components.topbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.campostexto.Buscador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarDefault(
    textoBusqueda: String = "",
    onTextoBusquedaChange: (String) -> Unit = {},
    cantidadItems: () -> Int,
    onNavigateTo: (String) -> Unit
){
    //Búsqueda + Lista Deseados + Carrito

    CenterAlignedTopAppBar(

        title = {

            Buscador(
                valor = textoBusqueda,
                placeholder = "Ingresa una dirección",
                onValueChange = onTextoBusquedaChange,
                onBuscarClick = {},//onNavigateTo,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 50.dp)
                    .padding(horizontal = 16.dp)
            )
        },

        actions = {
            NavigationListaDeseados(onNavigateTo)
            NavigationCarrito(
                cantidadItems = cantidadItems,
                onNavigateTo = onNavigateTo
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}