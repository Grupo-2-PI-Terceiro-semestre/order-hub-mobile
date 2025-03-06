package com.example.app_orderhub.ui.auth.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun TopImage(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val drawableId = context.resources.getIdentifier("fluido", "drawable", context.packageName)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .absoluteOffset(y = 0.dp)
            .zIndex(1f)
    ) {
        Image(
            painter = painterResource(id = drawableId),
            contentDescription = "Logo do App",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
