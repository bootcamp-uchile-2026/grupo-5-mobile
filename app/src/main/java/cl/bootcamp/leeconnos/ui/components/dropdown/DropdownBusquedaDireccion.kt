package cl.bootcamp.leeconnos.ui.components.dropdown

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.libraries.places.api.model.AutocompletePrediction

@Composable
fun DropdownBusquedaDireccion(
    visible: Boolean,
    listaOpciones: List<AutocompletePrediction>,
    onOpcionClick: (AutocompletePrediction) -> Unit,
) {

    AnimatedVisibility(
        visible = visible
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                //Limitar el alto del desplegable
                .heightIn(max = 140.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listaOpciones.size) { index ->
                    val prediccion = listaOpciones[index]

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onOpcionClick(prediccion)
                            }
                            .padding(16.dp)
                    ) {
                        Text(
                            text = prediccion.getPrimaryText(null).toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = prediccion.getSecondaryText(null).toString(),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    //Divisor entre opciones
                    if (index < listaOpciones.lastIndex) {
                        androidx.compose.material3.HorizontalDivider(
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }
        }
    }
}