package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.bootcamp.leeconnos.ui.components.AppTopBarDefault

@Composable
fun CatalogoScreen(
    onNavigateTo: (String) -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarDefault(
                onNavigateTo = onNavigateTo
            )
        }
    ) { paddingValues ->

        Text("Catalogo", modifier = Modifier.padding(paddingValues))

    }

}