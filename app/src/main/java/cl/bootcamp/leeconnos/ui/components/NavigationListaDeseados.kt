package cl.bootcamp.leeconnos.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun NavigationListaDeseados(
    onNavigateTo: (String) -> Unit
) {

    IconButton(
        onClick = {
            onNavigateTo(SecondaryRoutes.ListaDeseados.route)
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_corazon_deseados),
            contentDescription = "Ver lista de deseados",
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}