package com.example.app_orderhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.navigation.AppNavigation
import com.example.app_orderhub.util.theme.AppOrderHubTheme
import com.example.app_orderhub.viewmodel.SharedClientViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AppOrderHubTheme {
                val navController = rememberNavController()
                val sharedClientViewModel: SharedClientViewModel = viewModel()
                Scaffold (
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                ) { innerPadding -> AppNavigation(navController, innerPadding, sharedClientViewModel)
                }
            }
        }
    }
}

