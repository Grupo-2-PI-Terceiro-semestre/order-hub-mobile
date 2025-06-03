package com.example.app_orderhub.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_orderhub.domain.model.Schedule
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CardPast(
//    date: String = "Sab, 13 junho 2024",
//    serviceName: String = "Corte e Barba",
//    professionalName: String = "Com Kevin Silva",
//    status: String = "FINALIZADA",
//    imagemUrl: String = "https://www.barbeariank.com.br/wp-content/uploads/2021/06/Logo-Barbearia-NK-1.png",
    onReschedule: () -> Unit = {},
    schedule: Schedule = Schedule(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = formatIsoDateTime(schedule.dataHora ?: ""),
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 10.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Card(
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconProfile(
                            title = "${schedule.nomeEmpresa}",
                            fontSize = 16,
                            size = 40
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${schedule.nomeServico}",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }

                    Text(
                        text = "${schedule.status}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF2196F3),
                        modifier = Modifier.align(Alignment.Top)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { onReschedule() },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2185A8))
                ) {
                    Text(text = "Reagendar", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardPast() {
    CardPast()
}

fun formatIsoDateTime(isoDateTime: String): String {
    return try {
        val inputFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm")
        val dateTime = LocalDateTime.parse(isoDateTime, inputFormatter)
        dateTime.format(outputFormatter)
    } catch (e: Exception) {
        isoDateTime // Fallback se erro
    }
}