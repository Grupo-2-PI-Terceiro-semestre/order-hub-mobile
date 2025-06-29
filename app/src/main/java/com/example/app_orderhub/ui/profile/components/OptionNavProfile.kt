package com.example.app_orderhub.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.domain.model.Client
import com.example.app_orderhub.util.theme.ColorTextProfile

@Composable
fun OptionNavProfile(navController: NavController, idClient : Int?) {
    ProfileOptionsList(navController, idClient)
}


@Composable
fun ProfileOptionsList(navController: NavController, idClient : Int?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .background(Color.White)

    ) {

        ProfileOption(
            icon = Icons.Default.Person,
            title = "Minha Conta",
            subtitle = "Faça alterações em sua conta",
            endIconColor = Color.Red,
            onclick = { navController.navigate("editProfile/${idClient}") }
        )


//        ProfileOption(
//            icon = Icons.Default.Lock,
//            title = "Face ID / Touch ID",
//            subtitle = "Gerencie a segurança do seu dispositivo",
//            hasSwitch = true,
//            onclick = { }
//        )


        ProfileOption(
            icon = Icons.Default.ExitToApp,
            title = "Sair",
            endIcon = Icons.Default.ArrowForward,
            onclick = {
                navController.navigate("login") {
                    popUpTo("editProfile/${idClient.toString()}") {
                        inclusive = true
                    }
                }
            }
        )
    }
}

@Composable
fun ProfileOption(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    subtitle: String? = null,
    endIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    endIconColor: Color = Color.Gray,
    hasSwitch: Boolean = false,
    onclick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onclick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ColorTextProfile,
            modifier = Modifier.size(24.dp),
        )

        Spacer(modifier = Modifier.width(12.dp))


        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.Black
            )
            subtitle?.let {
                Text(
                    text = it,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        if (hasSwitch) {
            Switch(checked = false, onCheckedChange = {})
        }


        endIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                tint = endIconColor,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}
