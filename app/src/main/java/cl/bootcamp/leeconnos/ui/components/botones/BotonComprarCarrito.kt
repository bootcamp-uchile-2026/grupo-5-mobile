package cl.bootcamp.leeconnos.ui.components.botones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.bootcamp.leeconnos.R

@Composable
fun BotonPrincipalBottomBar(
    textoBtn: String,
    onComprarClick: () -> Unit
) {

//    Button(
//        colors = ButtonDefaults.buttonColors(
//            containerColor = MaterialTheme.colorScheme.primary
//        ),
//        onClick = {
//            onComprarClick()
//        },
//        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
//    ) {
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_carrito),
//                contentDescription = "",
//                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, end = 8.dp)
//            )
//
//            Text(
//                text = textoBtn,
//                style = MaterialTheme.typography.bodyMedium,
//                fontWeight = FontWeight.SemiBold
//            )
//        }
//    }
}