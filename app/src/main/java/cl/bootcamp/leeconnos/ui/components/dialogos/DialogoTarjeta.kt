package cl.bootcamp.leeconnos.ui.components.dialogos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DialogoTarjeta(
    onTarjetaCreditoClick: () -> Unit,
    onTarjetaDebitoPrepagoClick: () -> Unit,
    onDismiss: () -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            TituloDialogo(
                titulo = "Elegir método de pago",
                onCerrarClick = onDismiss
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            Text(
                text = "*Si tu tarjeta no tiene código CVV ponte en contacto con tu banco para obtenerlo",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            ItemDialogoTarjeta(
                titulo = "Crédito",
                onClick = onTarjetaCreditoClick
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

            ItemDialogoTarjeta(
                titulo = "Débito o prepago",
                onClick = onTarjetaDebitoPrepagoClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}