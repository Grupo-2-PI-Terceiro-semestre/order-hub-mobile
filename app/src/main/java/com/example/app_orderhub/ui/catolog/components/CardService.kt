package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.domain.model.Professional
import com.example.app_orderhub.domain.model.Service
import com.example.app_orderhub.util.theme.OrderHubBlue
import com.example.app_orderhub.viewmodel.SharedClientViewModel

@Composable
fun CardService(
    modifier: Modifier = Modifier,
    idEnterprise : String,
    service: Service,
    professional: List<Professional>,
    sharedClientViewModel : SharedClientViewModel,
    navController: NavController,
) {
    var showSchedule by remember { mutableStateOf(false) }

    val client = sharedClientViewModel.client.collectAsState().value
    val idClient = client?.idPessoa.toString()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = Color.Black.copy(alpha = 0.9f),
                spotColor = Color.Black.copy(alpha = 0.9f)
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.nomeServico,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Column {
                    Text(
                        text = "Profissional:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = getNameProfessionals(professional),
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    color = Color.Black,
                    text = service.precoServico.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.width(50.dp))
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    color = Color.Black,
                    text = service.duracaoServico,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                onClick = { showSchedule = true },
                modifier = Modifier
                    .height(40.dp)
                    .width(120.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(OrderHubBlue)
            ) {
                Text(
                    text = "Agendar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }

    if (showSchedule) {
        ScheduleModal(
            onDismiss = { showSchedule = false },
            idEnterprise,
            service.idServico.toString(),
            idClient,
            service,
            professional,
            dataHora = null,
            sharedClientViewModel,
            navController
        )
    }

    fun getNameProfessionals(professional: List<Professional>): String {
        return professional.joinToString(", ") { it.nomePessoa }.toString()
    }
}

