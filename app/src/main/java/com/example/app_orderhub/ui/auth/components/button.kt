package com.example.app_orderhub.ui.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_orderhub.util.theme.OrderHubBlue


@Composable
fun ButtonAuth(
    text: String = "",
    textColor: Color = Color.White,
    backgroundColor: Color = OrderHubBlue,
    borderColor: Color = Color.Black,
    borderRadius: Int = 20,
    borderWidth: Int = 2,
    width: Int = 200,
    height: Int = 50,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = RoundedCornerShape(borderRadius.dp),
        border = BorderStroke(borderWidth.dp, borderColor),
        modifier = Modifier
            .width(width.dp)
            .height(height.dp)
    ) {
        Text(text = text, color = textColor, style = TextStyle(fontSize = 16.sp))
    }
}

@Preview
@Composable
private fun PreviewButtonAuth() {
    ButtonAuth()
}
