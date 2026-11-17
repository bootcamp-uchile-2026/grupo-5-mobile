package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
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

            BotonPrincipal(
                textoBtn = "Continuar",
                onClick = onAgregarClick,
                modifier = Modifier.padding(horizontal = 16.dp),
                estiloTexto = MaterialTheme.typography.bodyMedium,
                contentPadding = PaddingValues(vertical = 12.dp)
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