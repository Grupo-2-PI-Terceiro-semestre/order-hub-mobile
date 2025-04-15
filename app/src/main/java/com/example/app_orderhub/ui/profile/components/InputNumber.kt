package com.example.app_orderhub.ui.profile.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app_orderhub.domain.model.Client
import com.example.app_orderhub.ui.profile.viewmodel.ProfileViewModel
import com.example.app_orderhub.util.formater.formatPhoneNumber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

@Composable
fun InputNumber(
    countryCode: String,
    modifier: Modifier = Modifier,
    vm: ProfileViewModel,
    client: Client
) {
    var countryInfo by remember { mutableStateOf<CountryInfo?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(countryCode) {
        countryInfo = fetchCountryInfo(countryCode)
        isLoading = false
    }

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        } else {
            AsyncImage(
                model = countryInfo?.flagUrl,
                contentDescription = "Bandeira do país",
                modifier = Modifier
                    .size(55.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(2.dp))

        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            modifier = Modifier
                .height(55.dp)
                .width(1.dp)
        )

        Spacer(modifier = Modifier.width(2.dp))

        TextField(
            value = formatPhoneNumber(client.numeroTelefone),
            label = {
                Text(text = "Número de Telefone", color = Color.Black)
            },

            textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
            modifier = modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray, // você pode trocar por outra cor
                    shape = RoundedCornerShape(4.dp) // opcional, para cantos arredondados
                )
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                val numberFormated = formatPhoneNumber(it)
                vm.onPhoneChanged(numberFormated)
            }
        )
    }
}

suspend fun fetchCountryInfo(countryCode: String): CountryInfo? {
    return withContext(Dispatchers.IO) {
        try {
            val apiUrl = "https://restcountries.com/v3.1/alpha/$countryCode"
            val response = URL(apiUrl).readText()
            val jsonArray = org.json.JSONArray(response)
            val countryData = jsonArray.getJSONObject(0)

            val idd = countryData.getJSONObject("idd")
            val root = idd.getString("root")
            val suffixes = idd.getJSONArray("suffixes")
            val phoneCode = root + suffixes.getString(0)

            val flagUrl = countryData.getJSONObject("flags").getString("png")

            CountryInfo(phoneCode, flagUrl)
        } catch (e: Exception) {
            Log.e("API_ERROR", "Erro ao buscar info: ${e.message}")
            null
        }
    }
}

data class CountryInfo(
    val phoneCode: String?,
    val flagUrl: String?
)

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun PhoneNumberComponentPreview() {
//    InputNumber(countryCode = "BR")
//}
