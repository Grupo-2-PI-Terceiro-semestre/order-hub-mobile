package com.example.app_orderhub.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_orderhub.domain.model.Client
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.profile.components.Inputs
import com.example.app_orderhub.ui.profile.components.OptionProfile
import com.example.app_orderhub.ui.profile.components.PicProfile
import com.example.app_orderhub.ui.profile.viewmodel.ProfileViewModel
import com.example.app_orderhub.util.components.CardLayout
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun EditProfileScreen(
    navController: NavController,
    idClient: String,
    profileViewModel: ProfileViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        profileViewModel.onIdClientChanged(idClient)
    }

    LaunchedEffect(Unit) {
        profileViewModel.getClient(
            onSuccess = {},
            onError = { /* Tratar erro */ }
        )
    }

    val client = profileViewModel.client.collectAsState()

    MenuNavigation(navController) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ColorBackGroundDefault)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    EditProfileScreenPrev(navController, client = client.value)
                }
            }
        }
}

@Composable
private fun EditProfileScreenPrev(navController: NavController, client: Client) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OptionProfile(navController)
        Spacer(modifier = Modifier.height(20.dp))
        PicProfile(client)
        Spacer(modifier = Modifier.height(20.dp))
        Inputs(client = client)
        Spacer(modifier = Modifier.height(70.dp))
        ButtonAuth(
            borderRadius = 10,
            width = 0.9f,
            text = "Salvar",
            backgroundColor = OrderHubBlue,
            textColor = Color.White,
            borderColor = OrderHubBlue,
            fontSize = 20,
            borderWidth = 1,
        )
        Spacer(modifier = Modifier.height(98.dp))
    }
}
