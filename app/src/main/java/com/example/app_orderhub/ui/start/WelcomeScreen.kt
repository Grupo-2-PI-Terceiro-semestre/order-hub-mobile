package com.example.app_orderhub.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_orderhub.ui.auth.components.ImageBottom

@Composable
fun WelcomeScreen(navController: NavController) {
    // LaunchedEffect para realizar a navegação com atraso
    LaunchedEffect(Unit) {
        // Atraso de 2 segundos antes de navegar
        kotlinx.coroutines.delay(2000)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    // Exibe a tela com o indicador de carregamento enquanto aguarda a navegação
    WelcomePrev()
}

@Preview(showBackground = true)
@Composable
private fun WelcomePrev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
                text = "Bem-vindo!",
                color = Color.Black
            )
            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.titleMedium,
                text = "Se prepare para explorar diversos serviços",
                color = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ) {
            ImageBottom()

            // Texto que aparece na parte inferior
            Text(
                text = "Vamos lá!",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
