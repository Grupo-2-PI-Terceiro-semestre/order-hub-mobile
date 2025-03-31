package com.example.app_orderhub.ui.catolog.components

import android.graphics.Bitmap
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_orderhub.services.loadImageFromUrl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext


@Composable
fun ImageEnterprise(
    modifier: Modifier = Modifier,
    nameEnterprise: String = "",
    urlImages: List<String> = emptyList()
) {
    val pagerState = rememberPagerState { urlImages.size }

    // Intervalo de tempo para mudar a imagem automaticamente com animação suave
    LaunchedEffect(pagerState) {
        while (true) {
            delay(3000) // Mudar a cada 3 segundos (ajuste conforme necessário)
            val nextPage = (pagerState.currentPage + 1) % urlImages.size
            pagerState.animateScrollToPage(nextPage, animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)) // Animação suave
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            beyondViewportPageCount = urlImages.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            var bitmap = remember { mutableStateOf<Bitmap?>(null) }
            var isLoading = remember { mutableStateOf(true) }

            LaunchedEffect(urlImages[page]) {
                isLoading.value = true
                bitmap.value = withContext(Dispatchers.IO) { loadImageFromUrl(urlImages[page]) }
                isLoading.value = false
            }

            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    bitmap = bitmap.value?.asImageBitmap() ?: ImageBitmap(1, 1),
                    contentDescription = "Imagem ${page + 1}",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                        .blur(16.dp)
                )

                if (isLoading.value) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
            }
        }

        // Indicador de páginas (bolinhas)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(urlImages.size) { index ->
                Box(
                    modifier = Modifier
                        .size(if (pagerState.currentPage == index) 16.dp else 10.dp) // Aumenta o tamanho da bolinha
                        .background(
                            if (pagerState.currentPage == index) Color.White else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        // Nome da empresa fixo, na parte inferior
        Text(
            text = nameEnterprise,
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 14.dp, bottom = 35.dp)
        )
    }
}





