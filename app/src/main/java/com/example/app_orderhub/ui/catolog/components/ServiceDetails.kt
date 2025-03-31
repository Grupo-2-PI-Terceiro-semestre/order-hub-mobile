package com.example.app_orderhub.ui.catolog.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Preview
//    (showBackground = true)
@Composable
fun ServiceDetail() {
    val uri = "https://portaleducacao.vteximg.com.br/arquivos/ids/163654/27.-Barbeiro-Profissional.png?v=638361846838600000"

    Card (modifier = Modifier
        .height(130.dp)
        .width(360.dp)
//        .background(Color.White)
        .shadow(
            elevation = 10.dp,
            shape = RoundedCornerShape(12.dp),
            ambientColor = Color.Black.copy(alpha = 0.9f),
            spotColor = Color.Black.copy(alpha = 0.9f)
        )
        .padding(5.dp)
        ,
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column (modifier = Modifier
            .fillMaxHeight()
            .padding(5.dp),
            verticalArrangement = Arrangement.SpaceAround) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {

                Text(
                    text = "Corte e Barba",
                    fontWeight = FontWeight.Bold
                )

                Column (modifier = Modifier
                    .height(50.dp),
                    verticalArrangement = Arrangement.SpaceAround
                    ) {
                    Text(
                        text = "R$40,00",
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "11:00 - 12:00",
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }

            }

            Text(text = "Profissional: Kevin Silva")

//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(uri)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = "Imagem de profissional",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)), // Garante que a imagem respeite a borda arredondada
////                placeholder = ColorPainter(Color.Gray)
//            )
        }

    }
}