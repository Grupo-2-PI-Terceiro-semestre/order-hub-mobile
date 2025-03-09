package com.example.app_orderhub.ui.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.data.Client
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.DividerSpace
import com.example.app_orderhub.ui.auth.components.ImageTop
import com.example.app_orderhub.ui.auth.components.Input
import com.example.app_orderhub.ui.auth.components.Title
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun RegisterScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RegisterScreenPreviw()
        }
    }
}

@Composable
fun RegisterScreenPreviw() {

    var client = remember { mutableStateOf(Client()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTop()

        Spacer(modifier = Modifier.height(16.dp))

        Title(title = "Faça seu cadastro")

        Spacer(modifier = Modifier.height(16.dp))

        Input(
            value = client.value.nome,
            onValueChange = { newValue -> client.value = client.value.copy(nome = newValue) },
            label = "Nome",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "AccountCircle Icon"
                )
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Input(
            value = client.value.sobrenome,
            onValueChange = { newValue -> client.value = client.value.copy(sobrenome = newValue) },
            label = "Sobrenome",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "AccountCircle Icon"
                )
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Input(
            value = client.value.email,
            onValueChange = { newValue -> client.value = client.value.copy(email = newValue) },
            label = "E-mail",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Lock Icon"
                )
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        Box() {
            ButtonAuth(
                borderRadius = 10,
                text = "Continuar",
                textColor = Color.Black,
                backgroundColor = Color.White,
                fontSize = 20,
                borderWidth = 1,
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { }) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF979797))) {
                        append("Já possui uma conta? ")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Entrar")
                    }
                },
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


       DividerSpace(content = "Continuar Usando")

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    RegisterScreenPreviw()
}

