package cl.bootcamp.leeconnos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.bootcamp.leeconnos.ui.screens.LeeconnosApp
import cl.bootcamp.leeconnos.ui.theme.LeeConNosTheme
import cl.bootcamp.leeconnos.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val mainViewModel: MainViewModel = koinViewModel()

            val isDark by mainViewModel.isDarkMode.collectAsStateWithLifecycle()

            LeeConNosTheme(darkTheme = isDark ?: isSystemInDarkTheme()) {
                LeeconnosApp(
                    mainViewModel = mainViewModel
                )
            }
        }
    }
}