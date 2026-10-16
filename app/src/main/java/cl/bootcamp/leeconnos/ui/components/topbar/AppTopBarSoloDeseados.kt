package cl.bootcamp.leeconnos.ui.components.topbar

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonIconoDeseados

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarSoloDeseados(
    modifier: Modifier = Modifier,
    estaEnListaDeseados: Boolean,
    onNavigateBack: () -> Unit,
    onDeseadosClick: () -> Unit
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

            BotonIconoDeseados(
                containerColor = Color.Black.copy(alpha = 0.4f),
                tint = Color.White,
                estaEnListaDeseados = estaEnListaDeseados,
                onClick = onDeseadosClick
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),

        windowInsets = WindowInsets(0.dp),

        modifier = modifier
    )
}