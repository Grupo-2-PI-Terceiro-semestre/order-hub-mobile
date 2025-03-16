package com.example.app_orderhub.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.util.components.CompanyCard
import com.example.app_orderhub.util.components.SearchBar
import com.example.app_orderhub.util.theme.ColorBackGroundDefault

@Composable
fun SearchScreen(navController: NavController) {
    val imageUrls = listOf(
        "https://static.vecteezy.com/ti/vetor-gratis/t1/10071559-barbearia-logo-barbearia-logo-modelo-vetor.jpg",
        "https://d2zdpiztbgorvt.cloudfront.net/region1/br/123891/biz_photo/ddfb1b419f3e43e1a8bb8bec1a348e-barbearia-o-barbado-unidade-2-biz-photo-e14961d6dfd549d6b79e481a8f3399-booksy.jpeg?size=640x427",
        "https://marketplace.canva.com/EAGIHtM5teQ/1/0/1600w/canva-logo-barbearia-minimalista-amarelo-e-preto-chpUtuXnSRA.jpg",
        "https://static.vecteezy.com/ti/vetor-gratis/t1/19495567-design-de-logotipo-de-barbearia-e-maquina-de-barbear-vetor.jpg",
        "https://elements-resized.envatousercontent.com/elements-cover-images/51ea8990-bfcb-4545-8108-486bcbc2a46e?w=433&cf_fit=scale-down&q=85&format=auto&s=cd3105e6ef22ff1dac4a367fd6b2172b1954ec36ef38ac88867a4986bf74fc73"
    )

    MenuNavigation(navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackGroundDefault),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            SearchBar("", {},
                Modifier
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Barbearia",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopCenter
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(10) { index ->
                        val imageUrl = imageUrls[index % imageUrls.size]

                        CompanyCard(
                            imagemUrl = imageUrl,
                            bairro = "Bairro $index",
                            nomeEmpresa = "Empresa $index",
                            servicos = "Serviço especializado $index",
                            isFullWidth = true,
                            modifier = Modifier
                                .fillMaxWidth(0.95f)
                                .height(200.dp)
                        )
                    }
                }
            }
        }
    }
}





@Composable
@Preview
fun PreviewSearchScreen() {
    SearchScreen(rememberNavController())
}