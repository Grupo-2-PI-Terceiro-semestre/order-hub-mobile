package com.example.app_orderhub.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_orderhub.data.Client
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.ImageTop
import com.example.app_orderhub.ui.auth.components.Input
import com.example.app_orderhub.ui.auth.components.TitleSubtitle
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun RecoverPasswordScreen(navController: NavController) {
    RecoverPasswordPreview(navController)
}

@Composable
fun RecoverPasswordPreview(navController: NavController) {
    var nome = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTop()

        Spacer(modifier = Modifier.height(30.dp))

        TitleSubtitle(
            title = "Esqueceu a Senha?",
            subtitle = "Enviaremos um e-mail para a autenticação e recuperação de senhaaaa",
            FontWeight.Bold,
            Color.Black,
            Color.Gray,
            25,
            widthPercentage = 0.7f
        )

        Spacer(modifier = Modifier.height(30.dp))

        var client = remember { mutableStateOf(Client()) }

        Input(
            value = client.value.email,
            onValueChange = { newValue -> client.value = client.value.copy(email = newValue) },
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
                // Lógica para enviar o e-mail de recuperação
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        ButtonAuth(
            borderRadius = 10,
            text = "Voltar",
            fontSize = 20,
            backgroundColor = Color.White,
            textColor = Color.Black,
            borderWidth = 1,
            onClick = {
                navController.navigate("login") {
                    popUpTo("recover") { inclusive = true }
                }
            }
        )
    }
}