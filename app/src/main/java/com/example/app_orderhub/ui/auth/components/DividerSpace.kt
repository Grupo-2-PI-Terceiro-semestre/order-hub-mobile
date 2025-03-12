package com.example.app_orderhub.ui.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DividerSpace (modifier: Modifier = Modifier , content: String = "") {

    val context = LocalContext.current
    val drawableId = context.resources.getIdentifier("icone_gmail", "drawable", context.packageName)

    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Row(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .weight(2f)
                    .height(1.dp)
            )
            Text(
                text = content,
                color = Color.Gray,
                fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 6.dp),
                textAlign = TextAlign.Center
            )
            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .weight(2f)
                    .height(1.dp)
            )
        }
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "gmail",
            contentScale = ContentScale.Fit, // Mantém a proporção
            modifier = Modifier.size(50.dp) // Define um tamanho fixo para evitar distorções
        )
    }

}