package com.example.app_orderhub.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.util.theme.ColorBackGroundDefault

@Composable
fun EditProfileScreen(navController: NavController) {
    MenuNavigation(navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackGroundDefault),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Tela Em Construção",
                color = Color.Black,
                fontSize = 24.sp
            )
        }
    }
}