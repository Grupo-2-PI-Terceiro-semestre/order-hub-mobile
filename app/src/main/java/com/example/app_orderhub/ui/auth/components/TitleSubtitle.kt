package com.example.app_orderhub.ui.auth.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TitleSubtitle(
//    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    fontWeight: FontWeight = FontWeight.Normal,
    colorTitle: Color = Color.Black,
    colorSubtitle: Color = Color.Gray,
    fontSizeTitle: Int = 15,
    widthPercentage: Float = .08f,
    textAlign: TextAlign = TextAlign.Justify
    ) {
    Text(
        style = MaterialTheme.typography.titleLarge.copy(
            fontSize = fontSizeTitle.sp,
            fontWeight = fontWeight,
            textAlign = TextAlign.Center,
            color = colorTitle
        ),
        text = title

    )

    Spacer(
        modifier = Modifier
            .height(20.dp)
    )

    Text(
        modifier = Modifier
            .fillMaxWidth(widthPercentage),

        style = MaterialTheme.typography.bodyMedium.copy(
            color = colorSubtitle,
            textAlign = textAlign
        ),


        text = subtitle
    )

}