package com.example.app_orderhub.ui.auth.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_orderhub.domain.model.Client
import com.example.app_orderhub.services.rememberImeState
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.DividerSpace
import com.example.app_orderhub.ui.auth.components.ImageTop
import com.example.app_orderhub.ui.auth.components.Input
import com.example.app_orderhub.ui.auth.components.Title
import com.example.app_orderhub.ui.auth.viewmodel.AuthViewModel
import com.example.app_orderhub.util.theme.ColorBackGroundDefault

@Composable
fun RegisterScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val imeState = rememberImeState()
    var scrollState = rememberScrollState()

    val client = authViewModel.client.collectAsState()
    val isLoading = authViewModel.isLoading.collectAsState()
    val errorMessage = authViewModel.errorMessage.collectAsState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            val incremento = 200
            val valorAlvo = (scrollState.value + incremento).coerceAtMost(scrollState.maxValue)
            scrollState.animateScrollTo(valorAlvo, tween(durationMillis = 300))
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            RegisterScreenPreviw(
                navController,
                authViewModel,
                client = client.value,
                isLoading = isLoading.value,
                errorMessage = errorMessage.value
            )
        }
    }
}

@Composable
fun RegisterScreenPreviw(
    navController: NavController,
    authViewModel: AuthViewModel,
    client: Client,
    isLoading: Boolean,
    errorMessage: String?
) {

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
            value = client.nomePessoa,
            onValueChange = authViewModel::onNameChanged,
            label = "Nome",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "AccountCircle Icon",
                    tint = Color.Black
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))
        Input(
            value = client.numeroTelefone,
            onValueChange = authViewModel::onPhoneChanged,
            label = "Número de telefone",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = "Phone Icon",
                    tint = Color.Black
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Input(
            value = client.emailPessoa,
            onValueChange = authViewModel::onEmailChanged,
            label = "E-mail",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Lock Icon",
                    tint = Color.Black
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Input(
            value = client.senha,
            onValueChange = authViewModel::onPasswordChanged,
            label = "Senha",
            widthPercentage = 0.8f,
            icon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Lock Icon",
                    tint = Color.Black
                )
            },
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        errorMessage?.let {
            Text(text = it, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Box() {
            ButtonAuth(
                borderRadius = 10,
                text = if (isLoading) "Aguarde..." else "Cadastrar",
                textColor = Color.Black,
                backgroundColor = Color.White,
                fontSize = 20,
                borderWidth = 1,
                onClick = {
                    if (!isLoading) {
                        authViewModel.register(
                            onSuccess = {
                                navController.navigate("login") {
                                    popUpTo("register") { inclusive = true }
                                }
                            },
                            onError = { /* Pode exibir erro na tela se necessário */ }
                        )
                    }
                }
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = {
            navController.navigate("login") {
                popUpTo("register") { inclusive = true }
            }
        }) {
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

