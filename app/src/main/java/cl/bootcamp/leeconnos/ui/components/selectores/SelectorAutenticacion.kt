package cl.bootcamp.leeconnos.ui.components.selectores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectorAutenticacion(
    esLogin: Boolean,
    onIniciarSesionClick: () -> Unit,
    onRegistroClick: () -> Unit,
    modifier: Modifier
) {

    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shadowElevation = 4.dp,
        modifier = modifier

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {

            BotonSelectorAutenticacion(
                textoBtn = "Iniciar sesión",
                activo = esLogin,
                onClick = onIniciarSesionClick,
                modifier = Modifier.weight(.5f)
            )
            
            BotonSelectorAutenticacion(
                textoBtn = "Crear una cuenta",
                activo = !esLogin,
                onClick = onRegistroClick,
                modifier = Modifier.weight(.5f)
            )
        }
    }
}