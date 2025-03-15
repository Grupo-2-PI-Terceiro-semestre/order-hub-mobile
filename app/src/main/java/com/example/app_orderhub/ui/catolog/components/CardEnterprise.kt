package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import com.example.app_orderhub.data.Enterprise
import com.example.app_orderhub.data.Service
import com.example.app_orderhub.util.theme.ColorBackGroundDefault


@Preview
@Composable
private fun CardEnterprisePreview() {
    CardEnterprise()
}


@Composable
fun CardEnterprise(
    modifier: Modifier = Modifier,
    enterprise: Enterprise = Enterprise()
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(8.dp)
            .offset(y = (-30).dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.9f),
                spotColor = Color.Black.copy(alpha = 0.9f)
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ){
            Text(
                color = Color.Black,
                fontSize = 14.sp,
                text = "${enterprise.endereco.logradouro}, ${enterprise.endereco.numero} - ${enterprise.endereco.bairro}, ${enterprise.endereco.cidade} - ${enterprise.endereco.uf}"
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                color = Color.Black,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                text = "Principais Serviços:"
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                color = Color.Black,
                fontSize = 13.sp,
                text = getNameServices(enterprise.servicos)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        MapView()
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(8.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            text = "Serviços"
        )

        // Exibe a lista de serviços da empresa
        enterprise.servicos.forEach { service ->
            CardService(
                titleService = service.nomeServico,
                professionalName = getNameProfessionals(service.proficional),
                valueService = service.precoServico,
                timeService = service.duracaoServico.toString(),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

fun getNameServices(services: List<Service>): String {
    return services.joinToString(", ") { it.nomeServico }
}

fun getNameProfessionals(professional: List<String>): String {
    return professional.joinToString(", ") { it.toString() }
}

