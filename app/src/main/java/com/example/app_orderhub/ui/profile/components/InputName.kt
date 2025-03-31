package com.example.app_orderhub.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun InputName(modifier: Modifier = Modifier) {
    var name by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
Column {

    CustomTextFild(modifier = Modifier
        .padding(12.dp)
        .background(Color.White)
        .clip(RoundedCornerShape(4.dp))
        .fillMaxWidth(),
        valueOf = name,
        labelText = "Qual o seu Primeiro Nome?",
        changeValue ={novoValor ->
            name = novoValor
        }
    )
    Spacer(modifier = Modifier.height(10.dp))

    CustomTextFild(modifier = Modifier
        .padding(12.dp)
        .clip(RoundedCornerShape(4.dp))
        .fillMaxWidth(),
        valueOf = lastName,
        labelText = "E seu Sobrenome?",
        changeValue ={novoValor ->
            lastName = novoValor
        }
    )
}
    }


@Preview
@Composable
private fun InputNamePrev() {
    InputName()
}


@Composable
fun CustomTextFild(
    modifier: Modifier = Modifier,
    valueOf : String,
    changeValue: (String) -> Unit,
    labelText: String,
) {
    TextField(
        value = valueOf,
        label = {
            Text(text = labelText)
        },
        modifier = modifier,
        onValueChange = {
            changeValue(it)
        }
    )
}