package cl.bootcamp.leeconnos.ui.components.topbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarFichaLibro(
    modifier: Modifier = Modifier,
    cantidadItemsCarrito: () -> Int,
    onNavigateBack: () -> Unit,
    onDeseadosClick: (String) -> Unit,
    onCarritoClick: (String) -> Unit
){

    CenterAlignedTopAppBar(

        title = {},

        navigationIcon = {
            NavigationArrowBack(
                onNavigateBack = onNavigateBack,
                containerColor = Color.Black.copy(alpha = 0.4f),
                tint = Color.White
            )
        },

        actions = {

            //Colores hardcodeados para mantener estilo en modo claro y oscuro

            NavigationListaDeseados(
                onNavigateTo = onDeseadosClick,
                containerColor = Color.Black.copy(alpha = 0.4f),
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(16.dp))

            NavigationCarrito(
                cantidadItems = cantidadItemsCarrito,
                onNavigateTo = onCarritoClick,
                containerColor = Color.Black.copy(alpha = 0.4f),
                tint = Color.White
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),

        windowInsets = WindowInsets(0.dp),
        modifier = modifier
    )
}