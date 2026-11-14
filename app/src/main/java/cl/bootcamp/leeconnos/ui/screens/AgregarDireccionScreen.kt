package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun AgregarDireccionScreen(
    onNavigateBack: () -> Unit,
    onContinuarClick: () -> Unit
) {

    Scaffold(
        topBar = {

            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.AgregarDireccion.title,
                onNavigateBack = onNavigateBack
            )
        },

        bottomBar = {

            BotonPrincipalBottomBar(
                textoBtn = "Continuar",
                onClick = onContinuarClick
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

        }
    }
}