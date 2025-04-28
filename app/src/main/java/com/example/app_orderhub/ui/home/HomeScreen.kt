package com.example.app_orderhub.ui.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.domain.model.Enterprise
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.services.rememberImeState
import com.example.app_orderhub.ui.home.viewmodel.HomeViewModel
import com.example.app_orderhub.util.components.CompanyCard
import com.example.app_orderhub.util.components.SearchBar
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.util.theme.LightGray
import com.example.app_orderhub.util.theme.OrderHubBlue

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {

    val param = homeViewModel.param.collectAsState()

    val enterprises = homeViewModel.enterprises.collectAsState()

    val enterprisesForCategory = homeViewModel.enterprisesForCategory.collectAsState()

    val isLoading = homeViewModel.isLoading.collectAsState()

    val isLoadingCategory = homeViewModel.isLoadingCategory.collectAsState()

    val imeState = rememberImeState()
    val scrollState = rememberScrollState()

    val categories = listOf(
        "Barbearias",
        "Salões de Beleza",
        "Esteticistas",
        "Manicure & Nail Designer",
        "Sobrancelhas & Cílios"
    )

    LaunchedEffect(Unit) {
        homeViewModel.getEnterprises(
            onSuccess = {},
            onError = { /* Pode exibir erro na tela se necessário */ }
        )
    }

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
                value = param.value.toString(),
                onValueChange = homeViewModel::onParamChanged,
                onSearchAction = {
                    homeViewModel.getEnterprises(
                        onSuccess = {},
                        onError = { /* Pode exibir erro na tela se necessário */ }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            Spacer(Modifier.height(20.dp))

            SectionTitle("Estabelecimentos Recomendados")

            // 🔹 Exibir o loading enquanto os dados estão carregando
            if (isLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp)
                    )
                }
            } else {
                HorizontalCompanyList(navController, enterprises.value ?: emptyList())
            }

            Spacer(Modifier.height(12.dp))

            SectionTitle("Categorias")
            CategorySelectionRow(categories, homeViewModel)

            Spacer(Modifier.height(12.dp))

            if (isLoadingCategory.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(100.dp)
                    )
                }
            } else {
                HorizontalCompanyList(navController, enterprisesForCategory.value ?: emptyList())
            }
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
fun HorizontalCompanyList(navController: NavController, enterprises: List<Enterprise>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .height(250.dp)
    ) {
        enterprises.forEach { enterprise ->
            CompanyCard(
                imagemUrl = enterprise.imagens.firstOrNull()
                    ?: "", // Pega a primeira imagem ou usa string vazia
                bairro = enterprise.endereco.bairro,
                nomeEmpresa = enterprise.nomeEmpresa,
                servicos = enterprise.servicos.joinToString(", ") { it.nomeServico }, // Concatena os nomes dos serviços
                modifier = Modifier
                    .padding(8.dp)
                    .width(180.dp),
                isFullWidth = false,
                onClick = { navController.navigate("catalog/${enterprise.idEmpresa}") }
            )
        }
    }
}


@Composable
fun CategorySelectionRow(
    categories: List<String>,
    homeViewModel: HomeViewModel
) {
    val categoryParam = homeViewModel.categoryParam.collectAsState()

    // Garante que a primeira categoria seja "Barbearia"
    LaunchedEffect(Unit) {
        homeViewModel.onParamWithCategoryChanged("Barbearias")
    }

    // Chama a API sempre que a categoria mudar
    LaunchedEffect(categoryParam.value) {
        homeViewModel.getEnterprisesForCategory(
            onSuccess = {},
            onError = { /* Pode exibir erro na tela se necessário */ }
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        categories.forEach { category ->
            CategoryButton(
                text = category,
                isSelected = category == categoryParam.value, // Agora acompanha corretamente o estado do ViewModel
                onClick = {
                    homeViewModel.onParamWithCategoryChanged(category) // Atualiza no ViewModel
                }
            )
        }
    }
}



@Composable
fun CategoryButton(
    text: String,
    isSelected: Boolean, // Adicionado para verificar se está ativo
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) OrderHubBlue else LightGray // Cor muda se estiver ativo
    val textColor = if (isSelected) Color.White else OrderHubBlue

    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(15.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        ),
        border = BorderStroke(1.dp, OrderHubBlue) // Mantém a borda fixa
    ) {
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(rememberNavController())
}