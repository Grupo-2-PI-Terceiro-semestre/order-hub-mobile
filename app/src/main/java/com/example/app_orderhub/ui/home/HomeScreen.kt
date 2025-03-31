package com.example.app_orderhub.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.services.rememberImeState
import com.example.app_orderhub.util.components.CompanyCard
import com.example.app_orderhub.util.components.SearchBar
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.LightGray
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun HomeScreen(navController: NavController) {

    val imeState = rememberImeState()
    var scrollState = rememberScrollState()

    LaunchedEffect(key1 = imeState.value) {
        if (imeState.value) {
            scrollState.animateScrollTo(scrollState.maxValue, tween(300))
        }
    }

    MenuNavigation(navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackGroundDefault)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            Spacer(Modifier.height(20.dp))

            SectionTitle("Estabelecimentos Recomendados")
            HorizontalCompanyList(navController)

            Spacer(Modifier.height(12.dp))

            SectionTitle("Categorias")
            HorizontalCategoryList()

            Spacer(Modifier.height(12.dp))
            HorizontalCompanyList(navController)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun HorizontalCompanyList(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        repeat(10) {
            CompanyCard(
                imagemUrl = "https://d159pl2qjrokxm.cloudfront.net/br/images/1144/cover_147379087988.jpeg",
                bairro = "Centro",
                nomeEmpresa = "Loja Exemplo",
                servicos = "Barba, Corte de Cabelo, Sobrancelha, etc.",
                modifier = Modifier
                    .padding(8.dp)
                    .width(180.dp),
                isFullWidth = false,
                onClick = { navController.navigate("catalog") }
            )
        }
    }
}

@Composable
fun HorizontalCategoryList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        CategoryButton("Barbearia", OrderHubBlue, Color.White)
        repeat(6) {
            CategoryButton("Categoria", LightGray, Color.Black)
        }
    }
}

@Composable
fun CategoryButton(text: String, backgroundColor: Color, textColor: Color) {
    Button(
        onClick = { },
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(15.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        border = BorderStroke(1.dp, backgroundColor)
    ) {
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}