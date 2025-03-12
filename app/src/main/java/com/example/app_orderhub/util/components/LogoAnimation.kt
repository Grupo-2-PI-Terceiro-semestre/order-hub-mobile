package com.example.app_orderhub.util.components

import android.net.Uri
import android.widget.ImageView
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.app_orderhub.R
import com.example.app_orderhub.util.theme.ColorBackGroundDefault


@Composable
fun LogoAnimation(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(ColorBackGroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        VideoPlayer(resourceId = R.raw.logo, modifier = Modifier.fillMaxWidth())
    }
}


@Composable
fun VideoPlayer(resourceId: Int, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            VideoView(context).apply {
                val uri = Uri.parse("android.resource://${context.packageName}/$resourceId")
                setVideoURI(uri)
                setOnPreparedListener { it.isLooping = true } // Faz o vídeo rodar em loop
                start()
            }
        }
    )
}
