package cl.bootcamp.leeconnos.ui.components.detalles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun DetalleConfirmacionCompra(
    textoPrimerItem: String,
    textoSegundoItem: String,
    iconoPrimerItem: Int,
    iconoSegundoItem: Int,
    primerItemSelected: Boolean,
    segundoItemSelected: Boolean,
    primerItemOnClick: () -> Unit,
    segundoItemOnClick: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {

        ItemMetodoEntrega(
            icono = iconoPrimerItem,
            texto = textoPrimerItem,
            selected = primerItemSelected,
            onClick = primerItemOnClick
        )

        ItemMetodoEntrega(
            icono = iconoSegundoItem,
            texto = textoSegundoItem,
            selected = segundoItemSelected,
            onClick = segundoItemOnClick
        )
    }
}