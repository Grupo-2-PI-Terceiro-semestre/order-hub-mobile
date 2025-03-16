package com.example.app_orderhub.util.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardLayout(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp), // Remova o .shadow() daqui
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp), // A elevação padrão do Card
        colors = CardDefaults.cardColors(Color.White)
    ) {
        content()
    }
}