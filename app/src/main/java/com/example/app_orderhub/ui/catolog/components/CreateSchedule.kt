package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.TitleSubtitle
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun Schedule(navController: NavController) {
    SchedulePreview()
}

@Preview(showBackground = true)
@Composable
fun SchedulePreview() {

    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .height(600.dp)
        .padding(0.dp, 15.dp))
    {

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            text = "Corte e Barba"
        )

        Spacer(modifier = Modifier
            .height(10.dp))

        Calendar()

        Row(modifier = Modifier
            .width(315.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Appointment()

            Appointment()

            Appointment()

            Appointment(backgroundApp = OrderHubBlue)
        }

        Spacer(modifier = Modifier
            .height(20.dp)
        )

        ServiceDetail()

        Spacer(modifier = Modifier
            .height(20.dp)
        )

        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .border(2.dp, Color.Red)
                .width(320.dp)
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
//                .border(2.dp, Color.Blue)
            ) {

                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    text = "R$40,00")

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = "1 Hora")
            }

            ButtonAuth(
                "Confirmar",
                borderColor = OrderHubBlue,
                borderRadius = 10,
                width = .6f,
                height = 35
            )
        }

    }
}