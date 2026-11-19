package cl.bootcamp.leeconnos.ui.components.botones

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun BotonIconoDeseados(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    tint: Color = MaterialTheme.colorScheme.onBackground,
    estaEnListaDeseados: Boolean,
    buttonSize: Dp = 48.dp,
    iconSize: Dp = 24.dp,
    onClick: () -> Unit
){

    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .offset(x = (-8).dp, y = 8.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .shadow(
                elevation = 12.dp,
                shape = MaterialTheme.shapes.extraLarge
            )
            .size(buttonSize),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        Icon(
            painter = if (estaEnListaDeseados) painterResource(R.drawable.ic_corazon_deseados_filled) else painterResource(R.drawable.ic_corazon_deseados),
            contentDescription = "Ver lista de deseados",
            tint = if(estaEnListaDeseados) Color(0xFFF86767) else tint,
            modifier = Modifier.size(iconSize)
        )
    }
}