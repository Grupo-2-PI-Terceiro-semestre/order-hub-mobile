package com.example.app_orderhub.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CompanyCard(
    imagemUrl: String,
    bairro: String,
    nomeEmpresa: String,
    servicos: String,
    isFullWidth: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .then(
                if (isFullWidth) Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                else Modifier
                    .height(200.dp)
                    .width(200.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(16.dp),
                clip = true,
                ambientColor = Color.Black.copy(alpha = 0.2f),
                spotColor = Color.Black.copy(alpha = 0.2f)
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(20.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imagemUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Imagem da empresa",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                placeholder = ColorPainter(Color.Gray)
            )

            // Texto ocupando 50% do espaço
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ocupa metade do card
                    .padding(12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = bairro,
                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.ExtraBold),
                    color = Color.Black.copy(alpha = 0.6f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = nomeEmpresa,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = servicos,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray.copy(alpha = 0.8f)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {
    CompanyCard(
        imagemUrl = "https://d159pl2qjrokxm.cloudfront.net/br/images/1144/cover_147379087988.jpeg",
        bairro = "Centro",
        nomeEmpresa = "Loja Exemplo",
        servicos = "Barba, Corte de Cabelo, Sobrancelha, etc.",
        modifier = Modifier.padding(16.dp),
        isFullWidth = false
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCardFullWidht() {
    CompanyCard(
        imagemUrl = "https://d159pl2qjrokxm.cloudfront.net/br/images/1144/cover_147379087988.jpeg",
        bairro = "Centro",
        nomeEmpresa = "Loja Exemplo",
        servicos = "Barba, Corte de Cabelo, Sobrancelha, etc.",
        modifier = Modifier.padding(16.dp),
        isFullWidth = true
    )
}