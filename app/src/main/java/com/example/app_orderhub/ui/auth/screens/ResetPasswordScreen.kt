package com.example.app_orderhub.ui.auth.screens

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.services.rememberImeState
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.ImageTop
import com.example.app_orderhub.ui.auth.components.TitleSubtitle
import com.example.app_orderhub.ui.auth.viewmodel.AuthViewModel
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    token: String,
    authViewModel: AuthViewModel = viewModel()
) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val errorMessage = authViewModel.errorMessage.collectAsState().value
    var showSuccessDialog by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        if (!token.isEmpty()) {
            authViewModel.onTokenChanged(token)
        }
    }

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
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageTop()

            Spacer(modifier = Modifier.height(24.dp))

            TitleSubtitle(
                title = "Recuperação de senha",
                subtitle = "Crie sua nova senha e volte a navegar em nossa plataforma :)",
                fontWeight = FontWeight.Bold,
                fontSizeTitle = 22,
                widthPercentage = 0.9f,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            PasswordInputField(
                label = "Senha",
                value = password,
                onValueChange = { password = it },
                isVisible = passwordVisible,
                onToggleVisibility = { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordInputField(
                label = "Confirmar Senha",
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                isVisible = confirmPasswordVisible,
                onToggleVisibility = { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(text = it, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }

            ButtonAuth(
                text = "Continuar",
                borderRadius = 10,
                borderColor = OrderHubBlue,
                onClick = {
                    if (password == confirmPassword && password.length >= 6) {
                        authViewModel.onPasswordChanged(password)
                        authViewModel.resetPassword(
                            onSuccess = {
                                showSuccessDialog = true
                            },
                            onError = { error ->
                                authViewModel.onMessageChanged(error)
                            }
                        )

                    } else {
                        authViewModel.onMessageChanged(
                            "As senhas não coincidem ou são muito curtas. Por favor, tente novamente."
                        )
                    }
                }
            )

            if (showSuccessDialog) {
                AlertDialog(
                    onDismissRequest = { showSuccessDialog = false },
                    confirmButton = {
                        androidx.compose.material3.TextButton(
                            onClick = {
                                showSuccessDialog = false
                                navController.navigate("login") {
                                    popUpTo(0) {
                                        inclusive = true
                                    }
                                }
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    title = { Text("Senha redefinida") },
                    text = { Text("Sua senha foi redefinida com sucesso.") }
                )
            }

        }
    }
}


@Composable
fun PasswordInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = label,
                            color = Color.Gray
                        )
                    }
                    innerTextField()
                },
                visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
            )

            Text(
                text = if (isVisible) "Ocultar" else "Mostrar",
                color = OrderHubBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { onToggleVisibility() }
                    .padding(8.dp)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun ResetPasswordScreenPreview() {
//    val navController = rememberNavController()
//    ResetPasswordScreen(navController = navController)
//}
