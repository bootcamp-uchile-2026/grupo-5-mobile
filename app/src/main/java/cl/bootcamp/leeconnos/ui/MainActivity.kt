package cl.bootcamp.leeconnos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.bootcamp.leeconnos.ui.screens.LeeconnosApp
import cl.bootcamp.leeconnos.ui.theme.LeeConNosTheme
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            val isDark by viewModel.isDarkMode.collectAsStateWithLifecycle()

            LeeConNosTheme(darkTheme = isDark ?: false) {
                LeeconnosApp(
                    viewModel = viewModel
                )
            }
        }
    }
}