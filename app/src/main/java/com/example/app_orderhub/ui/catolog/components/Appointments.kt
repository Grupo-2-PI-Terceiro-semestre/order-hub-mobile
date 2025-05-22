package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun Appointment(
    time : String = "",
    isSelected: Boolean,
    onClick: () -> Unit
) {
    AppointmentPreview(time, isSelected, onClick)
}

@Composable
fun AppointmentPreview(
    time: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) OrderHubBlue else Color.White
    val colorText = if (isSelected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .border(1.dp, Color.Black, shape = RoundedCornerShape(5.dp))
            .clip(RoundedCornerShape(5.dp))
            .width(80.dp)
            .height(40.dp)
            .background(backgroundColor)
            .clickable { onClick() }
            .drawBehind {
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Gray.copy(alpha = 0.5f), Color.Transparent),
                        startY = size.height,
                        endY = size.height + 8.dp.toPx()
                    ),
                    topLeft = Offset(0f, size.height),
                    size = Size(size.width, 8.dp.toPx())
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = time,
            style = TextStyle(textAlign = TextAlign.Center),
            color = colorText
        )
    }
}
