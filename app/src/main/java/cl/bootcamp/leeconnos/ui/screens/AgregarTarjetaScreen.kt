package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.bottombar.BottomBarSecundario
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

            BottomBarSecundario(
                content = {
                    BotonPrincipal(
                        textoBtn = "Continuar",
                        onClick = onAgregarClick,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        contentPadding = PaddingValues(vertical = 12.dp)
                    )
                }
            )
        },

        contentWindowInsets = WindowInsets.systemBars

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

        }
    }
}