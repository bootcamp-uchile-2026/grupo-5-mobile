package cl.bootcamp.leeconnos.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import cl.bootcamp.leeconnos.R

@Composable
fun NavigationArrowBack(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {

    IconButton(
        onClick = {
            onNavigateBack()
        },
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_arrow_back),
            contentDescription = "Atrás",
            tint = tint
        )
    }
}