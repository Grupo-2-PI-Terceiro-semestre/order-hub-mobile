package com.example.app_orderhub.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.app_orderhub.ui.auth.screens.ConfirmRecoverPasswordScreen
import com.example.app_orderhub.ui.auth.screens.LoginScreen
import com.example.app_orderhub.ui.auth.screens.RecoverPasswordScreen
import com.example.app_orderhub.ui.auth.screens.RegisterScreen
import com.example.app_orderhub.ui.catolog.CatalogScreen
import com.example.app_orderhub.ui.home.HomeScreen
import com.example.app_orderhub.ui.map.MapScreen
import com.example.app_orderhub.ui.profile.EditProfileScreen
import com.example.app_orderhub.ui.profile.ProfileScreen
import com.example.app_orderhub.ui.profile.SchedulingScreen
import com.example.app_orderhub.ui.search.SearchScreen
import com.example.app_orderhub.ui.start.SplashScreen
import com.example.app_orderhub.ui.start.WelcomeScreen
import com.example.app_orderhub.viewmodel.SharedClientViewModel


@Composable
fun AppNavigation(
    navController: NavHostController,
                  innerPadding: PaddingValues,
                  sharedClientViewModel: SharedClientViewModel
) {
    // TODO: ALTERAR AQUI PARA CONSEGUIR VISUALIZAR A TELA, COLOCAR O NOME DA TELA NO "startDestination"
    // TODO: NÃO SE ESQUEÇA DE INSTACIAR A TELA PRINCIPAL AQUI
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController, sharedClientViewModel) }
        composable("register") { RegisterScreen(navController) }
        composable("recover") { RecoverPasswordScreen(navController) }
        composable("confirmRecover") { ConfirmRecoverPasswordScreen(navController) }



        composable("home") { HomeScreen(navController) }
        composable("search") { SearchScreen(navController) }
        composable("profile") { ProfileScreen(navController, sharedClientViewModel) }
        composable("locale") { MapScreen(navController) }
        composable("scheduling") { SchedulingScreen(navController) }
        composable("catalog/{idEnterprise}") { backStackEntry ->
            val idEmpresa = backStackEntry.arguments?.getString("idEnterprise") ?: ""
            CatalogScreen(idEnterprise = idEmpresa, navController = navController)
        }

        composable("editProfile/{idClient}") { backStackEntry ->
            val idClient = backStackEntry.arguments?.getString("idClient") ?: ""
            EditProfileScreen(navController, idClient)
        }
    }
}