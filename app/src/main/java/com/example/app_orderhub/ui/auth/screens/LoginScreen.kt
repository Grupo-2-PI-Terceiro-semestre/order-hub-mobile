package com.example.app_orderhub.ui.auth.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    val imeState = rememberImeState()
    var scrollState = rememberScrollState()

    val client = authViewModel.client.collectAsState()

    val isLoading = authViewModel.isLoading.collectAsState()
    val errorMessage = authViewModel.errorMessage.collectAsState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopImagePreview()
            FormLogin(
                navController = navController,
                authViewModel =  authViewModel,
                client = client.value,
                isLoading =  isLoading.value,
                errorMessage =  errorMessage.value
            )
        }
    }
}



@Preview
@Composable
private fun LoginPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopImagePreview()
        }
    }
}


@Composable
private fun TopImagePreview() {
    ImageTop()
}

@Composable
private fun TitlePreview() {
    Title(
        title = "Entre em sua conta",
    )
}


@Composable
fun FormLogin(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    client: Client,
    isLoading: Boolean,
    errorMessage: String?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .imePadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier.height(16.dp))
            TitlePreview()
            Spacer(modifier.height(16.dp))
            Input(
                value = client.emailPessoa,
                onValueChange = authViewModel::onEmailChanged,
                label = "E-mail",
                widthPercentage = 0.8f,
                icon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "E-mail Icon",
                        tint = Color.Black
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
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
            Spacer(modifier.height(16.dp))

            errorMessage?.let {
                Text(text = it, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }

            ButtonRecoverPasswordPreview(navController)
            Spacer(modifier.height(16.dp))
            ButtonsPreview(navController, authViewModel, isLoading)
            Spacer(modifier.height(16.dp))
            DividerSpace(content = "Continuar Usando")
        }
    }
}



@Composable
private fun ButtonRecoverPasswordPreview(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(0.8f),
        contentAlignment = Alignment.CenterEnd
    ) {
        Text(
            text = "Esqueci minha Senha",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier.clickable {
                navController.navigate("recover")
            }
        )
    }
}


@Composable
private fun ButtonsPreview(navController: NavController, authViewModel: AuthViewModel, isLoading: Boolean) {
    Box {
        ButtonAuth(
            borderRadius = 10,
            text = if (isLoading) "Aguarde..." else "Entrar",
            borderColor = OrderHubBlue,
            fontSize = 20,
            onClick = {
//                if (!isLoading) {
//                    authViewModel.login(
//                        onSuccess = {
//                            navController.navigate("home") {
//                                popUpTo("login") { inclusive = true }
//                            }
//                        },
//                        onError = { /* Pode exibir erro na tela se necessário */ }
//                    )
//                }
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            }
        )
    }



    Spacer(Modifier.height(16.dp))
    Box {
        ButtonAuth(
            borderRadius = 10,
            text = "Cadastre-se",
            backgroundColor = Color.White,
            textColor = Color.Black,
            fontSize = 20,
            borderWidth = 1,
            onClick = {
                navController.navigate("register")
            }
        )
    }
}

