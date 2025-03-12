package com.example.app_orderhub.ui.components

import androidx.compose.foundation.BorderStroke
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
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun AppointmentCard(
    serviceName: String = "Corte e Barba",
    professionalName: String = "Com Kevin Silva",
    date: String = "Março",
    day: String = "15",
    time: String = "10:00",
    onCancel: () -> Unit = {},
    onReschedule: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = serviceName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = professionalName,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = date,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        text = day,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = time,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }

            IconProfile(
                title = "Barbearia NK",
                textColor = Color.Black,
                fontSize = 16,
                size = 40
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Red),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Cancelar", color = Color.Red)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = onReschedule,
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Alterar", color = Color.Black)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAppointmentCard() {
    AppointmentCard()
}
