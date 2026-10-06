package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    titulo: String?,
    mostrarBotonAtras: Boolean,
    mostrarActionIcons: Boolean,
    notificacionesSelected: Boolean,
    favoritosSelected: Boolean,
    carritoSelected: Boolean,
    onNavigateBack: () -> Unit,
    onNavigateToCarrito: () -> Unit,
    onNavigateToFavoritos: () -> Unit,
    onNavigateToNotificaciones: () -> Unit
) {

    TopAppBar(
        title = {

            if(titulo != null){
                Text(
                    text = titulo
                )

            } else {

                TextField(
                    value = "",
                    onValueChange = {},
                    label = {},
                    shape = RoundedCornerShape(50),
                    singleLine = true,
                    readOnly = true, //Solo para hito 1
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        },

        navigationIcon = {
            if(mostrarBotonAtras){
                AppNavigationIconArrowBack(onNavigateBack)
            }
        },

        actions = {
            if (mostrarActionIcons) {

                AppActionIconNotificaciones(notificacionesSelected, onNavigateToNotificaciones)
                AppActionIconFavoritos(favoritosSelected, onNavigateToFavoritos)
                AppActionIconCarrito(carritoSelected, onNavigateToCarrito)
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}