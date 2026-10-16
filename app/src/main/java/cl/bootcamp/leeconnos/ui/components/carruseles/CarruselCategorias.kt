package cl.bootcamp.leeconnos.ui.components.carruseles

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CarruselCategorias(
    listaCategorias: List<String>
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {

        items(listaCategorias) { categoria ->

            Text(
                text = categoria,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = MaterialTheme.shapes.small
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            )
        }
    }
}