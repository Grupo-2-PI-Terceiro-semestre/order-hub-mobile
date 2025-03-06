package com.example.app_orderhub.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.app_orderhub.ui.auth.LoginScreen
import com.example.app_orderhub.ui.auth.components.ImageBottom


@Composable
fun WelcomeScreen(navController: NavController) {

}
@Preview(showBackground = true)
@Composable
private fun WelcomePrev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.titleLarge,
                text = "Bem vindo!"
            )
            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier,
                style = MaterialTheme.typography.titleMedium,
                text = "Se prepare para explorar diversos serviços"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            contentAlignment = Alignment.Center
        ){
            ImageBottom()

//            TextButton() {

            Text(
                text = "Vamos lá!",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
            }
//        }
    }
}
