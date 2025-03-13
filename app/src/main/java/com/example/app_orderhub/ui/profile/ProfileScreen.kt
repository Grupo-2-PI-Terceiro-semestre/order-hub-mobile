package com.example.app_orderhub.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.profile.components.OptionNavProfile
import com.example.app_orderhub.ui.profile.components.ProfileCard
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import OptionProfile

@Composable
fun ProfileScreen(navController:NavController) {
    MenuNavigation(navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackGroundDefault),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileScreenPrev()
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
Column(modifier = Modifier
    .fillMaxSize()
    .background(ColorBackGroundDefault)
) {
    Text(
        text = "Perfil",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    )

    ProfileCard()

    Spacer(modifier = Modifier.height(16.dp))

    OptionNavProfile()

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = "Mais",
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    OptionProfile()

}
}