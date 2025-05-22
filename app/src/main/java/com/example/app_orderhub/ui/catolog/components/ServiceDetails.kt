package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.app_orderhub.domain.model.Professional
import com.example.app_orderhub.domain.model.Service


@Composable
fun ServiceDetail(
    service: Service,
    agendamento: AgendamentoSelecionado
) {
    Card(
        modifier = Modifier
            .padding(5.dp)
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
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = service.nomeServico,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.End, // alinhamento à direita
                ) {
                    Text(
                        text = "R$${service.precoServico}",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = if (agendamento.data != null && agendamento.horario != null) {
                            "${agendamento.data} às ${agendamento.horario}"
                        } else {
                            ""
                        },
                        fontWeight = FontWeight.Light,
                        color = Color.Gray
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = agendamento.profissional?.nomePessoa ?: "Profissional não selecionado",
                    color = Color.Black
                )
            }
        }
    }
}

data class AgendamentoSelecionado(
    val profissional: Professional? = null,
    val horario: String? = null,
    val data: String? = null
)
