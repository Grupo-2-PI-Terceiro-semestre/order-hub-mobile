package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Schedule(navController: NavController) {
    SchedulePreview()
}

@Preview(showBackground = true)
@Composable
fun SchedulePreview() {

    Column (modifier = Modifier
        .height(1000.dp)
        .padding(0.dp, 15.dp)){

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
            .fillMaxWidth()
//            .border(2.dp, Color.Black)
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Appointment()

//            Spacer(
//                modifier = Modifier
//                    .height(10.dp)
//            )

            Appointment()

//            Spacer(
//                modifier = Modifier
//                    .height(10.dp)
//
//            )
            Appointment()

            Appointment(backgroundApp = Color.Blue)
        }

    }
}