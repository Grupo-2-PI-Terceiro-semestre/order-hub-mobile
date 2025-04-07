package com.example.app_orderhub.ui.profile.components

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun InputNumber(countryCode: String, modifier: Modifier = Modifier) {
    var phoneCode by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var numberUser by remember { mutableStateOf("") }

    LaunchedEffect(countryCode) {
        phoneCode = fetchCountryPhoneCode(countryCode)
        isLoading = false
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        } else {
            Text(
                text = phoneCode ?: "N/A",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme
                    .onSurface
                    .copy(alpha = 0.5f),
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier
                .height(24.dp)
                .width(1.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        CustomTextFild(modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(4.dp))
            .fillMaxWidth(),
            valueOf = numberUser,
            labelText = "Número de Telefone",
            changeValue ={novoValor ->
                numberUser = novoValor
            }
        )
    }
}

suspend fun fetchCountryPhoneCode(countryCode: String): String? {
    return withContext(Dispatchers.IO) {
        try {
            val apiUrl = "https://restcountries.com/v3.1/alpha/$countryCode"
            val response = URL(apiUrl).readText()
            val jsonArray = org.json.JSONArray(response) // Corrigindo: recebe um array JSON
            val countryData = jsonArray.getJSONObject(0) // Pegando o primeiro objeto dentro do array
            val idd = countryData.getJSONObject("idd") // Pegando a seção de códigos de chamada
            val root = idd.getString("root") // Exemplo: "+5"
            val suffixes = idd.getJSONArray("suffixes") // Exemplo: ["5"]
            val phoneCode = root + suffixes.getString(0) // Combina root + primeiro sufixo
            phoneCode
        } catch (e: Exception) {
            Log.e("API_ERROR", "Erro ao buscar DDD: ${e.message}")
            null
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PhoneNumberComponentPreview() {
    InputNumber(countryCode = "BR")
}
