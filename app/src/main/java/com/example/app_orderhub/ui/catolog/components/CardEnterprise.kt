@file:Suppress("UNCHECKED_CAST")

package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_orderhub.domain.model.Enterprise
import com.example.app_orderhub.domain.model.Professional
import com.example.app_orderhub.domain.model.Service
import com.example.app_orderhub.ui.map.MapScreen
import com.example.app_orderhub.ui.map.viewmodel.MapViewModel
import kotlinx.coroutines.delay


//@Preview
//@Composable
//private fun CardEnterprisePreview() {
//    CardEnterprise()
//}


@Composable
fun CardEnterprise(
    modifier: Modifier = Modifier,
    enterprise: Enterprise = Enterprise(),
    viewModel: MapViewModel = viewModel()
) {

    LaunchedEffect(enterprise.endereco) {
        if (enterprise.endereco.logradouro.isNotEmpty()) {
            viewModel.setLocation(enterprise)
        }
    }

    val isLoading = remember { mutableStateOf(true) }
    val timeoutReached = remember { mutableStateOf(false) }



    val locale by viewModel.locations.collectAsState()
    val name by viewModel.name.collectAsState()

    LaunchedEffect(locale) {
        delay(10_000) // Espera 10 segundos
        if (locale.isEmpty()) {
            timeoutReached.value = true
            isLoading.value = false
        }
    }


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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            if (locale.isEmpty()) {
                CircularProgressIndicator()
            } else {
                MapView(locale[0], name.getOrElse(0) { "Localização não encontrada" })
            }
        }

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
                professionalName = getNameProfessionals(enterprise.proficionais),
                valueService = service.precoServico.toString(),
                timeService = service.duracaoServico,
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

fun getNameServices(services: List<Service>): String {
    return services.joinToString(", ") { it.nomeServico }.toString()
}

fun getNameProfessionals(professional: List<Professional>): String {
    return professional.joinToString(", ") { it.nomePessoa }.toString()
}

