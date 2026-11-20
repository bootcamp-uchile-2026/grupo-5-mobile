package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.data.modelos.Resena
import cl.bootcamp.leeconnos.ui.components.botones.BotonNavegacionSecundario
import cl.bootcamp.leeconnos.ui.components.carruseles.CarruselResenas

@Composable
fun SeccionResenas(
    resena: Resena,
    onNavigateTo: (String) -> Unit
){

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        EncabezadoValoracionResena(
            resena = "${resena.calificacion}"
        )

        SubtituloValoracionResena()

        CarruselResenas(
            resena = resena
        )

        BotonNavegacionSecundario(
            textoBtn = "Mostrar 34 reseñas", //listaResenas.size
            onNavigateTo = onNavigateTo
        )

        Text(
            text = "¿Cómo funcionan las reseñas?",
            style = MaterialTheme.typography.labelSmall,
            textDecoration = TextDecoration.Underline
        )

        HorizontalDivider(modifier = Modifier.padding(top = 16.dp, bottom = 24.dp))
    }
}