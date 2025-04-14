package com.example.app_orderhub.ui.profile

import android.renderscript.ScriptGroup.Input
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.ui.profile.components.InputName
import com.example.app_orderhub.ui.profile.components.InputNumber
import com.example.app_orderhub.ui.profile.components.OptionProfile
import com.example.app_orderhub.ui.profile.components.PicProfile


@Composable
fun EditProfileScreen(navController: NavController) {
Column {

    OptionProfile()
    Spacer(modifier = Modifier.height(20.dp))
    PicProfile()
    Spacer(modifier = Modifier.height(20.dp))
    InputName()
    Spacer(modifier = Modifier.height(20.dp))
    InputNumber("+55")
    Spacer(modifier = Modifier.height(20.dp))

}
}

@Preview(showBackground = true)
@Composable
private fun EditProfileScreenPrev() {
    EditProfileScreen(navController = rememberNavController())
}
