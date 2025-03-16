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
fun CardPast(
    date: String = "Sab, 13 junho 2024",
    serviceName: String = "Corte e Barba",
    professionalName: String = "Com Kevin Silva",
    status: String = "FINALIZADA",
    onReschedule: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = date,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Card(
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.Black),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                IconProfile(
                    title = "Barbearia NK",
                    textColor = Color.Black,
                    fontSize = 16,
                    size = 30
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = status,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF2196F3)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = serviceName,
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))

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

@Preview
@Composable
fun PreviewCardPast() {
    CardPast()
}
