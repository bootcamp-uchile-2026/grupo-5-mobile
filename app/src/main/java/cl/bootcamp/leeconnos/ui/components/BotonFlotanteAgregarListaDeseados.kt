package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun BotonFlotanteAgregarListaDeseados(
    estaEnListaDeseados: Boolean = false,
    modifier: Modifier,
    onIconButtonClick: () -> Unit
){

    IconButton(
        modifier = modifier
            .offset(x = (-8).dp, y = 8.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .shadow(
                elevation = 12.dp,
                shape = MaterialTheme.shapes.extraLarge
            )
            .background(color = MaterialTheme.colorScheme.surface),
        onClick = {
            onIconButtonClick()
        }
    ) {
        Icon(
            painterResource(id = R.drawable.ic_corazon_deseados),
            contentDescription = "Agregar a lista deseados"
        )
    }
}