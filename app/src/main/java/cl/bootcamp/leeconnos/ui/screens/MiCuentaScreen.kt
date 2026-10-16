package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.bootcamp.leeconnos.ui.components.topbar.AppTopBarSimple
import cl.bootcamp.leeconnos.ui.screens.screendata.ScreenRoutes
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

@Composable
fun MiCuentaScreen(
    mainViewModel: MainViewModel
){

    val isDark by mainViewModel.isDarkMode.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            AppTopBarSimple(
                titulo = ScreenRoutes.MI_CUENTA.title
            )
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { mainViewModel.toggleDarkMode(isDark) }
                    ) {
                        Icon(
                            imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Cambiar tema"
                        )
                    }
                }
            }
        }
    }
}