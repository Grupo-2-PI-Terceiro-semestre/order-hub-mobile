package com.example.app_orderhub.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_orderhub.data.Client
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.Input
import com.example.app_orderhub.ui.auth.components.TitleSubtitle
import com.example.app_orderhub.ui.auth.components.TopImage
import com.example.app_orderhub.util.theme.ColorBackGroundDefault


@Composable
fun LoginScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault), // Defina a cor de fundo aqui
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopImagePreview()
            FormLoginPreview()
            InputButtonPreview()
        }

    }

}

@Preview
@Composable
fun LoginPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Image fixa no topo
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopImagePreview()
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Título "Entre em sua conta"
            TitlePreview()

            Spacer(modifier = Modifier.height(24.dp))

            // Formulário de Login
            FormLoginPreview()

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de entrada
            InputButtonPreview()

            Spacer(modifier = Modifier.height(16.dp))

            // Botão de cadastro
            OutlinedButton (
                onClick = { /* Ação de cadastro */ },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Cadastre-se", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(24.dp))




            Spacer(modifier = Modifier.height(16.dp))

            // Botão Google
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Google Login",
                modifier = Modifier
                    .size(50.dp)
                    .clickable() { /* Login com Google */ }
            )
        }
    }
}




@Preview
@Composable
private fun TopImagePreview() {
    TopImage()
}

@Preview(showBackground = true)
@Composable
private fun TitlePreview() {
    TitleSubtitle(
        title = "Entre em sua conta",
        fontWeight = FontWeight.Bold,
        fontSizeTitle = 25
    )
}

@Preview
@Composable
private fun InputButtonPreview() {
    ButtonAuth(
        text = "Entrar"
    )
}


@Preview(showBackground = true)
@Composable
private fun FormLoginPreview() {
    FormLogin()
}


@Composable
fun FormLogin(modifier: Modifier = Modifier) {
    var client = remember { mutableStateOf(Client()) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Input(
                value = client.value.email,
                onValueChange = { newValue -> client.value = client.value.copy(email = newValue) },
                label = "E-mail",
                widthPercentage = 0.8f,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "E-mail Icon"
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Input(
                value = client.value.senha,
                onValueChange = { newValue -> client.value = client.value.copy(senha = newValue) },
                label = "Senha",
                widthPercentage = 0.8f,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock Icon"
                    )
                },
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }
}
