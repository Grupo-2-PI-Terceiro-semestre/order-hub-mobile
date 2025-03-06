package com.example.app_orderhub.ui.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import com.example.app_orderhub.util.theme.OrderHubBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: @Composable (() -> Unit)? = null,
    width: Dp? = null,
    widthPercentage: Float? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colorBorderInput: Color = OrderHubBlue
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = when {
            width != null -> Modifier.width(width)
            widthPercentage != null -> Modifier.fillMaxWidth(widthPercentage)
            else -> Modifier.fillMaxWidth()
        },
        trailingIcon = icon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Gray,
            focusedBorderColor = colorBorderInput,
            unfocusedBorderColor = Color.Gray
        ),
        textStyle = TextStyle(color = Color.Black),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        visualTransformation = visualTransformation
    )
}

