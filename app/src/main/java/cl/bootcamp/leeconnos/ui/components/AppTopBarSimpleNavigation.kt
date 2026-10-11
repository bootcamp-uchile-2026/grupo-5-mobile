package cl.bootcamp.leeconnos.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBarSimpleNavigation(
    titulo: String,
    onNavigateBack: () -> Unit
) {


    TopAppBar(

        title = { Text(text = titulo) },

        navigationIcon = {
            NavigationArrowBack(onNavigateBack)
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),

        windowInsets = WindowInsets(0.dp)
    )
}