package cl.bootcamp.leeconnos.ui.components.topbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun NavigationListaDeseados(
    onNavigateTo: (String) -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {

    IconButton(
        onClick = {
            onNavigateTo(SecondaryRoutes.ListaDeseados.route)
        },
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_corazon_deseados),
            contentDescription = "Ver lista de deseados",
            tint = tint
        )
    }
}