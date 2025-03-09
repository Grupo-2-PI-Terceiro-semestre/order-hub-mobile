package com.example.app_orderhub.ui.auth.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    modifier: Modifier = Modifier,
    title: String = "",
) {
    Box (
        Modifier.fillMaxWidth(0.8f)
    ){  }
    Text(
        text = title,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 25.sp,
        color = Color.Black
    )
}