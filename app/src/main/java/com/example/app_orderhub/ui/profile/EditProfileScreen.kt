package com.example.app_orderhub.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app_orderhub.ui.profile.components.OptionProfile
import com.example.app_orderhub.ui.profile.components.PicProfile

@Composable
fun EditProfileScreen(modifier: Modifier = Modifier) {
Column {

    OptionProfile()
    Spacer(modifier = Modifier.height(20.dp))
    PicProfile()
    Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview(showBackground = true)
@Composable
private fun EditProfileScreenPrev() {
    EditProfileScreen()
}
