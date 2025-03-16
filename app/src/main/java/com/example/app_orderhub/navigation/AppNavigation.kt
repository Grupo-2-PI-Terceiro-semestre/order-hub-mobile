package com.example.app_orderhub.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_orderhub.ui.auth.ConfirmRecoverPasswordScreen
import com.example.app_orderhub.ui.auth.LoginScreen
import com.example.app_orderhub.ui.auth.RecoverPasswordScreen
import com.example.app_orderhub.ui.auth.RegisterScreen
import com.example.app_orderhub.ui.catolog.CatalogScreen
import com.example.app_orderhub.ui.home.HomeScreen
import com.example.app_orderhub.ui.notification.NotificationScreen
import com.example.app_orderhub.ui.profile.EditProfileScreen
import com.example.app_orderhub.ui.profile.ProfileScreen
import com.example.app_orderhub.ui.profile.SchedulingScreen
import com.example.app_orderhub.ui.search.SearchScreen
import com.example.app_orderhub.ui.start.SplashScreen
import com.example.app_orderhub.ui.start.WelcomeScreen


@Composable
fun AppNavigation(navController: NavHostController, innerPadding: PaddingValues) {
    // TODO: ALTERAR AQUI PARA CONSEGUIR VISUALIZAR A TELA, COLOCAR O NOME DA TELA NO "startDestination"
    // TODO: NÃO SE ESQUEÇA DE INSTACIAR A TELA PRINCIPAL AQUI
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("recover") { RecoverPasswordScreen(navController) }
        composable("confirmRecover") { ConfirmRecoverPasswordScreen(navController) }



        composable("home") { HomeScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("profile") { ProfileScreen(navController) }
        composable("notification") { NotificationScreen(navController) }
        composable("scheduling") { SchedulingScreen(navController) }
        composable("catalog") { CatalogScreen(navController) }
        composable("editProfile") { EditProfileScreen(navController) }
    }
}