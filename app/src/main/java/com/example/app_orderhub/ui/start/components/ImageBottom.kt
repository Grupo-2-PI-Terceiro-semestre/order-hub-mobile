package com.example.app_orderhub.ui.start.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource

@Composable
fun ImageBottom(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val drawableId = context.resources.getIdentifier("fluidoinvertido", "drawable", context.packageName)
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = "Imagem de fundo do app",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
    )
}