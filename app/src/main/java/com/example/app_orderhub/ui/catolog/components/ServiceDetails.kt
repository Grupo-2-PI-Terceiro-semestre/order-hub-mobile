package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_orderhub.domain.model.Service


@Composable
fun ServiceDetail(service: Service) {
    val uri =
        "https://portaleducacao.vteximg.com.br/arquivos/ids/163654/27.-Barbeiro-Profissional.png?v=638361846838600000"

    Card(
        modifier = Modifier
            .padding(5.dp) // Padding aplicado primeiro
            .height(130.dp)
            .width(360.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.9f),
                spotColor = Color.Black.copy(alpha = 0.9f)
            ),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(5.dp),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = service.nomeServico,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Column(
                    modifier = Modifier
                        .height(50.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = "R$${service.precoServico}",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = "",
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }

            }

            Text(
                text = "",
                color = Color.Black
            )
        }
    }
}