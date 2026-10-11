package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.ui.components.AppTopBarSimpleNavigation
import cl.bootcamp.leeconnos.ui.screens.screendata.SecondaryRoutes

@Composable
fun ListaDeseadosScreen(
    onNavigateBack: () -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarSimpleNavigation(
                titulo = SecondaryRoutes.ListaDeseados.title,
                onNavigateBack = { onNavigateBack() }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        }
    }
}