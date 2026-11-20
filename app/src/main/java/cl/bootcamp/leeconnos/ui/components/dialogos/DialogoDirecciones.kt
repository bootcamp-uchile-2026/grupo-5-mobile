package cl.bootcamp.leeconnos.ui.components.dialogos

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.data.modelos.Direccion
import cl.bootcamp.leeconnos.ui.components.campostexto.Buscador
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipal
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes
import com.google.android.libraries.places.api.model.AutocompletePrediction

@Composable
fun DialogoDirecciones(
    textoBusqueda: String,
    sugerenciasPlaces: List<AutocompletePrediction>,
    textoUbicacionActual: String,
    tienePermisoUbicacion: Boolean,
    debeMostrarRationale: Boolean,
    listaDirecciones: List<Direccion>,
    onTextoBusquedaChange: (String) -> Unit,
    onPrediccionSeleccionada: (AutocompletePrediction) -> Unit,
    onNuevaDireccion: (String) -> Unit,
    onDireccionGuardada: (Direccion) -> Unit,
    onPedirPermiso: () -> Unit,
    onDismiss: () -> Unit
) {

    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
    ) {

        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {

            item {
                TituloDialogo(
                    titulo = "Direcciones",
                    onCerrarClick = onDismiss
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            }

            item {

                Buscador(
                    valor = textoBusqueda,
                    placeholder = "Ingresa una dirección",
                    onValueChange = { nuevoValor ->
                        onTextoBusquedaChange(nuevoValor)
                    },
                    onBuscarClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 50.dp)
                        .padding(horizontal = 16.dp)
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))
            }

            items(sugerenciasPlaces) { prediccion ->

                ItemDialogoDirecciones(
                    titulo = prediccion.getPrimaryText(null).toString(),
                    subtitulo = prediccion.getSecondaryText(null).toString(),
                    onClick = {
                        onPrediccionSeleccionada(prediccion)
                    }
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 24.dp))
            }

            //Controla el flujo completo del permiso para la ubicación
            item {

                if (tienePermisoUbicacion) {
                    ItemDialogoDirecciones(
                        titulo = "Usar mi ubicación actual",
                        subtitulo = textoUbicacionActual,
                        onClick = {
                            onNuevaDireccion(SecondaryRoutes.AgregarDireccion.route)
                        }
                    )

                //Dar razón del porqué se necesita el permiso (Solo se muestra cuando se rechaza el permiso)
                } else if (debeMostrarRationale) {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Para sugerirte direcciones cercanas y acelerar tu compra, necesitamos usar tu ubicación",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Button(
                            onClick = {
                                onPedirPermiso()
                            }
                        ) {
                            Text(
                                text = "Sí, dar permiso",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }

                } else {

                    BotonPrincipal(
                        textoBtn = "Usar mi ubicación",
                        onClick = {
                            onPedirPermiso()
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }


            //Por ahora hardcodeadas por falta de endpoint para manejar direcciones guardadas
            items(listaDirecciones) { direccion ->

                //item de lista
                ItemDialogoDirecciones(
                    titulo = "${direccion.calle}, ${direccion.comuna}, ${direccion.region}",
                    subtitulo = "${direccion.nombreReceptor} \n${direccion.telefono}",
                    onClick = { onDireccionGuardada(direccion) }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}