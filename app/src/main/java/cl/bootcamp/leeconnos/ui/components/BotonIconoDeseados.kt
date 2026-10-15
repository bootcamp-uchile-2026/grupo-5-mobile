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
fun BotonIconoDeseados(
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Transparent,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    estaEnListaDeseados: Boolean,
    onClick: () -> Unit
){

    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = containerColor
        )
    ) {
        Icon(
            painter = if (estaEnListaDeseados) painterResource(R.drawable.ic_corazon_deseados_filled) else painterResource(R.drawable.ic_corazon_deseados),
            contentDescription = "Ver lista de deseados",
            tint = if(estaEnListaDeseados) Color(0xFFF86767) else tint
        )
    }
}