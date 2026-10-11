package cl.bootcamp.leeconnos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.bootcamp.leeconnos.R
import cl.bootcamp.leeconnos.ui.components.AppTopBarDefault
import cl.bootcamp.leeconnos.ui.components.SeccionBienvenida

@Composable
fun HomeScreen(
    onNavigateTo: (String) -> Unit
){

    Scaffold(
        topBar = {
            AppTopBarDefault(
                onNavigateTo = onNavigateTo
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
                .padding(16.dp)
        ) {

            item {
                SeccionBienvenida(
                    nombre = "Maxi"//TODO: Cambiar por DataSource
                )
            }


            item {

                Text(
                    text = "Recomendados de nuestros libreros",
                    style = MaterialTheme.typography.labelMedium
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    items(count = 10) {

                        Box(
                            modifier = Modifier.padding(top = 8.dp, start = 8.dp)
                        ) {

                            Card(
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                                shape = MaterialTheme.shapes.large,
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier.width(180.dp)
                            ) {
                                Column {

                                    Image(
                                        painter = painterResource(id = R.drawable.fondo_transparente),
                                        contentDescription = "Titulo del libro",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1.2f)
                                            .clip(
                                                RoundedCornerShape(
                                                    topStart = 16.dp,
                                                    topEnd = 16.dp
                                                )
                                            )
                                    )

                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                    ) {
                                        Text(
                                            text = "Titulo del libro",
                                            style = MaterialTheme.typography.titleSmall,
                                            minLines = 2,
                                            maxLines = 2,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier
                                                .padding(bottom = 8.dp)
                                        )

                                        Text(
                                            text = "Autor",
                                            style = MaterialTheme.typography.labelSmall,
                                            fontWeight = FontWeight.Bold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )

                                        Row(
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.ic_star_filled), // Reemplazar por el tuyo
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )

                                            Text(
                                                text = "(12 opiniones)",
                                                style = MaterialTheme.typography.labelSmall,
                                                modifier = Modifier.padding(horizontal = 4.dp)
                                            )
                                        }
                                    }
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .offset(x = 4.dp, y = 4.dp)
                                    .clip(shape = MaterialTheme.shapes.extraLarge)
                                    .background(color = MaterialTheme.colorScheme.background)
                                    .padding(vertical = 4.dp, horizontal = 8.dp)
                            ) {
                                // Mini círculo o avatar de ejemplo
                                Image(
                                    painter = painterResource(id = R.drawable.fondo_transparente),
                                    contentDescription = "Foto del librero",
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(MaterialTheme.shapes.extraLarge)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Gregorio S.",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}