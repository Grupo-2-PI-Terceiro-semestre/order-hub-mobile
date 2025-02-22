package com.example.app_orderhub.ui.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val screenState = remember { mutableIntStateOf(0) } // Estado para controlar o que exibir

    LaunchedEffect(Unit) {
        delay(timeMillis = 3000)
        screenState.intValue = 0

        delay(timeMillis = 3000)
        screenState.intValue = 1

        delay(timeMillis = 3000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // UI com base no estado
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (screenState.intValue) {
            0 -> Text(text = "LOGO AQUI") // Substitua pela logo real
            1 -> Text(text = "Bem-vindo! Aguardando você para começar.") // Mensagem de boas-vindas
        }
    }
}
