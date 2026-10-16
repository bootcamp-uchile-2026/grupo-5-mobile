package cl.bootcamp.leeconnos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.bootcamp.leeconnos.ui.screens.LeeconnosApp
import cl.bootcamp.leeconnos.ui.theme.LeeConNosTheme
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel = MainViewModel()

        setContent {
            LeeConNosTheme {
                LeeconnosApp(
                    viewModel = mainViewModel
                )
            }
        }
    }
}