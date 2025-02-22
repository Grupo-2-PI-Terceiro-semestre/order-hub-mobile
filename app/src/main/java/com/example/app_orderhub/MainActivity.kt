package com.example.app_orderhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_orderhub.util.theme.AppOrderHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppOrderHubTheme {
                EsqueciASenha()
            }
        }
    }
}

@Preview
@Composable
fun esqueciASenhaPreview() {
    EsqueciASenha()
}

@Composable
fun EsqueciASenha() {
    var nome = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .fillMaxHeight(),
//        .border(2.dp, Color.Blue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
//            modifier = Modifier
//                .border(2.dp, Color.Red),
            style = MaterialTheme.typography.titleLarge.copy(
            fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
        ),
            text = "Esqueceu a senha?"

        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Text(
            modifier = Modifier
//                .border(2.dp, Color.Green)
                .width(280.dp),
            style = MaterialTheme.typography.bodyMedium,


            text = "Enviaremos um e-mail para a autenticação e recuperação de senha"
        )

        Spacer(
            modifier = Modifier
                .height(30.dp)
        )

        TextField(
            colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color.White ),
            
            modifier = Modifier
                .height(50.dp)
                .background(Color.White)
                .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp)),
            value = nome.value,
            label = {
                Text(
                    text = "username@orderhub.com"
                )
            },
            onValueChange = {
                    novoValor ->
                nome.value = novoValor
            }

        )

        Spacer(
            modifier = Modifier
                .height(30.dp)
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .width(285.dp)
                .height(50.dp),
            onClick = {}) {
            Text(
                text = "Enviar",

                style = MaterialTheme.typography.titleLarge,
            )
        }

    }
}