package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Appointment(
    backgroundApp: Color = Color.White
//    borderApp: border: BorderStroke? = null
) {
    AppointmentPreview(backgroundApp)
}

//@Preview
@Composable
fun AppointmentPreview(backgroundApp: Color) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(10.dp))
            .width(60.dp)
            .height(20.dp)
            .background(backgroundApp)
            .drawBehind {
                // Desenha a sombra abaixo do Box
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Gray.copy(alpha = 0.5f), Color.Transparent),
                        startY = size.height,
                        endY = size.height + 8.dp.toPx()
                    ),
                    topLeft = Offset(0f, size.height),
                    size = Size(size.width, 8.dp.toPx())
                )
            }
//            .padding(bottom = 3.dp)
        ,
        contentAlignment = Alignment.Center,
    ) {
        Text(
            modifier = Modifier
//                .border(1.dp, Color.Black)
//                .width(50.dp)
//                .height(40.dp)
            ,
            text = "10:00",
            style = TextStyle(
                textAlign = TextAlign.Center
            )
        )
    }
}