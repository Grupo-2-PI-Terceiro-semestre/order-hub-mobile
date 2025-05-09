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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
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
        BottomNavItem("scheduling", Icons.Default.DateRange, "Agenda"),
        BottomNavItem("search", Icons.Default.Search, "Buscar"),
        BottomNavItem("locale", Icons.Default.LocationOn, "Mapa"),
        BottomNavItem("profile", Icons.Default.Person, "Perfil")
    )

    val currentRoute = navController.currentDestination?.route

    // Detecta se o teclado está visível
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    // Controla a visibilidade do menu de navegação
    if (!isKeyboardVisible) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(ColorBackGroundDefault)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            ) {
                val width = size.width
                val height = size.height
                val waveWidth = 90.dp.toPx()
                val waveHeight = 55.dp.toPx()
                val centerX = width / 2

                val path = Path().apply {
                    moveTo(0f, 0f)
                    lineTo(centerX - waveWidth / 2, 0f)

                    cubicTo(
                        centerX - waveWidth / 2 + 20f, waveHeight + 10f,
                        centerX + waveWidth / 2 - 20f, waveHeight + 10f,
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
                    val isSelected = item.route == currentRoute
                    val iconColor = if (isSelected) Color.Black else Color.White
                    val sizeIcon = if (isSelected) 30.dp else 25.dp

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
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(sizeIcon),
                            tint = iconColor
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun FloatingCenterButton(navController: NavController) {
    // Detecta se o teclado está visível
    val isKeyboardVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0

    // Exibe o botão apenas se o teclado não estiver visível
    if (!isKeyboardVisible) {
        FloatingActionButton(
            onClick = { navController.navigate("home") },
            containerColor = ColorButtonNavigation,
            contentColor = Color.White,
            modifier = Modifier
                .size(80.dp)
                .offset(y = 55.dp)
                .shadow(8.dp, CircleShape)
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = Icons.Default.Home,
                contentDescription = "Home"
            )
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuNavigation(navController: NavController, content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = { FloatingCenterButton(navController) },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            content(paddingValues)
        }
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