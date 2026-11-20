package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.PlantillaResultadoTransaccion
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.components.bottombar.BottomBarSecundario

@Composable
fun ResultadoTransaccionScreen(
    esExito: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateToHome: () -> Unit
) {

    Scaffold(
        bottomBar = {
            BottomBarSecundario(
                content = {
                    BotonPrincipal(
                        textoBtn = "Ir al home",
                        onClick = onNavigateToHome,
                        textStyle = MaterialTheme.typography.bodyMedium,
                        textFontWeight = FontWeight.Bold,
                        contentPadding = PaddingValues(vertical = 20.dp)
                    )
                }
            )
        }
    ) { paddingValues ->

        if (esExito) {

            PlantillaResultadoTransaccion(
                icono = R.drawable.ic_check_grande,
                titulo = "Orden confirmada",
                subtitulo = "Tu pedido fue confirmado. Te enviaremos los detalles de la compra y el seguimiento por correo.",
                textoBtnSecundario = "Detalles de la compra",
                onBtnSecundarioClick = onNavigateToHome, //Por ahora que navegue al Home por falta de wireframe y pantalla detalle
                modifier = Modifier.padding(paddingValues))

        } else {

            PlantillaResultadoTransaccion(
                icono = R.drawable.ic_close,
                titulo = "No pudimos completar tu compra",
                subtitulo = "Ocurrió un problema al procesar el pago. Inténtalo nuevamente.",
                textoBtnSecundario = "Reintentar",
                onBtnSecundarioClick = onNavigateBack,
                modifier = Modifier.padding(paddingValues))
        }
    }
}