package com.example.app_orderhub.ui.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_orderhub.R
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.auth.components.ImageTop
import com.example.app_orderhub.ui.auth.components.TitleSubtitle
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun MobileTokenScreen(navController: NavController) {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTop()

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.checked),
            contentDescription = "Código de confirmação",
            modifier = Modifier.size(60.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TitleSubtitle(
            title = "Código de confirmação",
            subtitle = "Insira o código de recuperação para redefinir sua senha.",
            fontWeight = FontWeight.Bold,
            fontSizeTitle = 22,
            widthPercentage = 0.9f,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        StepIndicator(currentStep = code.length)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = code,
            onValueChange = {
                if (it.length <= 4 && it.all { c -> c.isDigit() }) {
                    code = it
                }
            },
            label = { Text("Código") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonAuth(
            text = "OK",
            borderRadius = 10,
            borderColor = OrderHubBlue,
            onClick = {
                if (code.length == 4) {
                    navController.popBackStack("confirmRecover", inclusive = true)
                    navController.popBackStack("recover", inclusive = true)
                    navController.navigate("login")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MobileTokenScreenPreview() {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTop()

        Spacer(modifier = Modifier.height(16.dp))

        TitleSubtitle(
            title = "Código de confirmação",
            subtitle = "Insira o código de recuperação para redefinir sua senha.",
            fontWeight = FontWeight.Bold,
            fontSizeTitle = 22,
            widthPercentage = 0.9f,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        StepIndicator(currentStep = code.length)

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = code,
            onValueChange = {
                if (it.length <= 4 && it.all { c -> c.isDigit() }) {
                    code = it
                }
            },
            label = { Text("Código") }
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonAuth(
            text = "Continuar",
            borderRadius = 15,
            borderColor = OrderHubBlue,
            onClick = {}
        )
    }
}

@Composable
fun StepIndicator(currentStep: Int, totalSteps: Int = 4) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 16.dp)
    ) {
        repeat(totalSteps) { index ->
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(12.dp))
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = if (index < currentStep) Color.Black else Color.Transparent,
                            shape = CircleShape
                        )
                        .border(
                            width = 1.5.dp,
                            color = if (index < currentStep) Color.Black else Color.LightGray,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}