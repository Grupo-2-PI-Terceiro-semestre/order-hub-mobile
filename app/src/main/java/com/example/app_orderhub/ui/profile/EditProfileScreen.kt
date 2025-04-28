package com.example.app_orderhub.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
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
                EditProfileScreenPrev(navController, profileViewModel)
            }
        }
    }
}


@Composable
private fun EditProfileScreenPrev(
    navController: NavController,
    profileViewModel: ProfileViewModel
) {

    val showSuccessDialog = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OptionProfile(navController)
        Spacer(modifier = Modifier.height(63.dp))
        PicProfile()
        Spacer(modifier = Modifier.height(20.dp))
        Inputs(vm = profileViewModel)
        Spacer(modifier = Modifier.height(70.dp))
        ButtonAuth(
            borderRadius = 10,
            width = 0.9f,
            text = "Salvar",
            backgroundColor = OrderHubBlue,
            textColor = Color.White,
            borderColor = OrderHubBlue,
            onClick = {
                profileViewModel.editClient(
                    onSuccess = {
                        showSuccessDialog.value = true
                    },
                    onError = { /* Tratar erro */ }
                )
            },
            fontSize = 20,
            borderWidth = 1,
        )
        Spacer(modifier = Modifier.height(98.dp))
    }

    if (showSuccessDialog.value) {
        AlertDialog(
            onDismissRequest = { showSuccessDialog.value = false },
            title = {
                Text(
                    text = "Sucesso",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            text = {
                Text(
                    text = "Informações salvas com sucesso!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { showSuccessDialog.value = false },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Text(
                        text = "OK",
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        )
    }
}
