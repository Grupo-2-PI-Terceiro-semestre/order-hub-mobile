package com.example.app_orderhub.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_orderhub.data.Client
import com.example.app_orderhub.ui.auth.LoginScreen
import com.example.app_orderhub.ui.auth.RegisterScreen
import com.example.app_orderhub.ui.start.SplashScreen


@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
    }
}