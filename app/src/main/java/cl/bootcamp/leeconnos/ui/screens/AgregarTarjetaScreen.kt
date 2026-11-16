package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalBottomBar
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun AgregarTarjetaScreen(
    onNavigateBack: () -> Unit,
    onAgregarClick: () -> Unit
) {

    Scaffold(
        topBar = {

            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.AgregarTarjeta.title,
                onNavigateBack = onNavigateBack
            )
        },

        bottomBar = {

            BotonPrincipalBottomBar(
                textoBtn = "Continuar",
                onClick = onAgregarClick
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