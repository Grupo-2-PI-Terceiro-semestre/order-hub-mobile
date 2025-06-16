package com.example.app_orderhub.ui.auth.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun ConfirmRecoverPasswordScreen(navController: NavController) {
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
            contentDescription = "E-mail enviado com sucesso",
            modifier = Modifier.size(60.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TitleSubtitle(
            title = "E-mail enviado!",
            subtitle = "Aguarde o e-mail para realizar a \nrecuperação de senha",
            fontWeight = FontWeight.Bold,
            fontSizeTitle = 22,
            widthPercentage = 0.9f,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonAuth(
            text = "OK",
            borderRadius = 10,
            borderColor = OrderHubBlue,
            onClick = {
                navController.popBackStack("confirmRecover", inclusive = true)
                navController.popBackStack("recover", inclusive = true)
                navController.navigate("token")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmRecoverPasswordPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageTop()

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.checked),
            contentDescription = "E-mail enviado com sucesso",
            modifier = Modifier.size(60.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TitleSubtitle(
            title = "E-mail enviado!",
            subtitle = "Aguarde o e-mail para realizar a \nrecuperação de senha",
            fontWeight = FontWeight.Bold,
            fontSizeTitle = 22,
            widthPercentage = 0.9f,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonAuth(
            text = "OK",
            borderRadius = 15,
            borderColor = OrderHubBlue,
            onClick = {}
        )
    }
}


