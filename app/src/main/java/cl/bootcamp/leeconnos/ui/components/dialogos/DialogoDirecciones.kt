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
import cl.bootcamp.leeconnos.ui.components.Buscador
import cl.bootcamp.leeconnos.ui.components.ItemDirecciones
import cl.bootcamp.leeconnos.ui.components.botones.BotonPrincipalConIcono
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun DialogoDirecciones(
    textoBusqueda: String,
    tienePermisoUbicacion: Boolean,
    debeMostrarRationale: Boolean,
    listaDirecciones: List<Direccion>,
    onTextoBusquedaChange: (String) -> Unit,
    onNuevaDireccion: (String) -> Unit,
    onDireccionGuardada: () -> Unit,
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

            //Controla el flujo completo del permiso para la ubicación
            item {

                if (tienePermisoUbicacion) {
                    ItemDirecciones(
                        titulo = "Usar mi ubicación actual",
                        subtitulo = "Av. Siempre viva 1666, Santiago, Región Metropolitana Chile", //Cambiar por ubicación basada en longitud y latitud
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

                    Spacer(modifier = Modifier.height(24.dp))

                } else {

                    BotonPrincipalConIcono(
                        textoBtn = "Usar mi ubicación",
                        icono = R.drawable.ic_plus, //Placeholder mientras se obtiene el icono de ubicación
                        onBtnClick = {
                            onPedirPermiso()
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }


            items(listaDirecciones) { direccion ->

                //item de lista
                ItemDirecciones(
                    titulo = "${direccion.calle}, ${direccion.comuna}, ${direccion.region}",
                    subtitulo = "[NOMBRE(CAMBIAR)] \n${direccion.telefono}",
                    onClick = onDireccionGuardada
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}