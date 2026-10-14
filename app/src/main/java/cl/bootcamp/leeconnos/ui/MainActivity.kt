package cl.bootcamp.leeconnos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.bootcamp.leeconnos.ui.screens.LeeConNosApp
import cl.bootcamp.leeconnos.ui.theme.LeeConNosTheme
import cl.bootcamp.leeconnos.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel = MainViewModel()

        setContent {
            LeeConNosTheme {
                LeeConNosApp(
                    viewModel = mainViewModel
                )
            }
        }
    }
}