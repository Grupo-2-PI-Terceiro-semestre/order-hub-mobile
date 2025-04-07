package com.example.app_orderhub.ui.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.app_orderhub.R

@Composable
fun PicProfile() {
val nomeUsuario = "Cliente"
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "Foto de Perfil",
            modifier = Modifier
                .size(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = nomeUsuario,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PicProfilePreview(){
    PicProfile()
}
