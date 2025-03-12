package com.example.app_orderhub.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.util.theme.ColorButtonNavigation
import com.example.app_orderhub.util.theme.OrderHubBlue


@Preview(showBackground = true)
@Composable
fun PreviewBottomNavigationBar() {
    val navController = rememberNavController()


}


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("agenda", Icons.Default.DateRange, "Agenda"),
        BottomNavItem("search", Icons.Default.Search, "Buscar"),
        BottomNavItem("notifications", Icons.Default.Notifications, "Notificações"),
        BottomNavItem("profile", Icons.Default.Person, "Perfil")
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
        ) {
            val width = size.width
            val height = size.height
            val waveWidth = 100.dp.toPx()
            val waveHeight = 65.dp.toPx()
            val centerX = width / 2

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(centerX - waveWidth, 0f) // Vai até o início da primeira curva
                cubicTo(
                    centerX - 0, 0f, // Ponto de controle 1 da primeira curva
                    centerX - 190.dp.toPx() / 2, 65.dp.toPx(), // Ponto de controle 2 da primeira curva
                    centerX - 105.dp.toPx() / 2, 40f // Fim da primeira curva
                )
                cubicTo(
                    centerX - waveWidth / 2, waveHeight,
                    centerX + waveWidth / 2, waveHeight,
                    centerX + waveWidth / 2, 0f
                )
                lineTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(path, color = OrderHubBlue)
        }

        BottomAppBar(
            modifier = Modifier.height(80.dp),
            containerColor = Color.Transparent // Transparente para exibir a curva desenhada
        ) {
            items.forEachIndexed { index, item ->
                if (index == 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
                IconButton(
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = item.icon, contentDescription = item.label, tint = Color.White)
                }
            }
        }
    }
}



@Composable
fun FloatingCenterButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("home") },
        containerColor = ColorButtonNavigation,
        contentColor = Color.White,
        modifier = Modifier
            .size(80.dp)
            .offset(y = (55).dp)
            .shadow(8.dp, CircleShape)
    ) {
        Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenContent(navController: NavController) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) },
            floatingActionButton = { FloatingCenterButton(navController) },
            floatingActionButtonPosition = FabPosition.Center
        ) {}
    }
}

@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenContentPreview() {
    val navController = rememberNavController()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Scaffold(
            bottomBar = { BottomNavigationBar(navController) },
            floatingActionButton = { FloatingCenterButton(navController) },
            floatingActionButtonPosition = FabPosition.Center
        ) {}
    }
}

data class BottomNavItem(val route: String, val icon: ImageVector, val label: String)