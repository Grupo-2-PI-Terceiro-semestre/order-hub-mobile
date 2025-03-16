package com.example.app_orderhub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.R

@Composable
fun IconProfile(
    imageRes: Int = R.drawable.barbearia,
    title: String = "",
    textColor: Color = Color.Black,
    fontSize: Int = 20,
    size: Int = 50
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Logo da Barbearia NK",
            modifier = Modifier.size(size.dp)
        )

        Spacer(modifier = Modifier.width(4.dp)) // Reduzi o espaçamento aqui

        Text(
            text = title,
            color = textColor,
            style = TextStyle(fontSize = fontSize.sp, fontWeight = FontWeight.Bold)
        )
    }
}


@Preview
@Composable
private fun PreviewBarberShopLogo() {
    IconProfile()
}
