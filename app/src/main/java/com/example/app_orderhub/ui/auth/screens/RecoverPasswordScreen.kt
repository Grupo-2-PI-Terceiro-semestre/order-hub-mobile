package com.example.app_orderhub.ui.auth.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_orderhub.domain.model.Client
import com.example.app_orderhub.services.rememberImeState
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.ImageTop
import com.example.app_orderhub.ui.auth.components.Input
import com.example.app_orderhub.ui.auth.components.TitleSubtitle
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun RecoverPasswordScreen(navController: NavController) {
    val imeState = rememberImeState()
    var scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RecoverPasswordPreview(navController)
        }
    }

}

@Composable
fun RecoverPasswordPreview(navController: NavController) {

    Column(
        modifier = Modifier
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTop()
        Spacer(modifier = Modifier.height(16.dp))
        TitleSubtitle(
            title = "Esqueceu a Senha?",
            subtitle = "Enviaremos um e-mail para a autenticação e recuperação de senha",
            FontWeight.Bold,
            Color.Black,
            Color.Gray,
            25,
            widthPercentage = 0.7f
        )

        Spacer(modifier = Modifier.height(30.dp))

        var client = remember { mutableStateOf(Client()) }

        Input(
            value = client.value.emailPessoa,
            onValueChange = { newValue -> client.value = client.value.copy(emailPessoa = newValue) },
            label = "username@orderhub.com",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "E-mail Icon"
                )
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        ButtonPreview(navController)
    }
}

@Composable
private fun ButtonPreview(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        ButtonAuth(
            borderRadius = 10,
            text = "Enviar",
            borderColor = OrderHubBlue,
            fontSize = 20,
            onClick = {
                navController.navigate("confirmRecover")
            }
        )
    }
}