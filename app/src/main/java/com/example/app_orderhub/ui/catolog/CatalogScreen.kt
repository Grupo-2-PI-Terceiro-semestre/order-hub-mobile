package com.example.app_orderhub.ui.catolog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.data.Address
import com.example.app_orderhub.data.Enterprise
import com.example.app_orderhub.data.Service
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.catolog.components.CardEnterprise
import com.example.app_orderhub.ui.catolog.components.CardService
import com.example.app_orderhub.ui.catolog.components.ImageEnterprise
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import java.time.LocalTime


@Preview
@Composable
private fun CatalogScreenPreview() {
    CatalogScreen(rememberNavController())
}

@Composable
fun CatalogScreen(navController: NavController) {
    MenuNavigation(navController) {

        val enterprise = initEnterprise()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackGroundDefault)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageEnterprise(
                nameEnterprise = "Barbearia NK",
                urlImageEnterprise = "https://bmcontba.com.br/wp-content/uploads/2023/06/IMAGEM-BLOG-DPG-14.jpg"
            )
            CardEnterprise(enterprise = enterprise)
        }
    }
}

fun initEnterprise() = Enterprise(
    nomeEmpresa = "Barbearia Alpha",
    endereco = Address(
        logradouro = "Rua Principal",
        cidade = "São Paulo",
        bairro = "Centro",
        uf = "SP",
        cep = "00000-000",
        numero = "123"
    ),
    servicos = listOf(
        Service(
            idServico = 1,
            nomeServico = "Corte de Cabelo",
            duracaoServico = LocalTime.of(0, 30),
            descricaoServico = "Corte masculino tradicional",
            precoServico = "R$ 30,00",
            proficional = listOf("João", "Lucas")
        ),
        Service(
            idServico = 2,
            nomeServico = "Barba Completa",
            duracaoServico = LocalTime.of(0, 20),
            descricaoServico = "Modelagem e acabamento da barba",
            precoServico = "R$ 25,00",
            proficional = listOf("Marcos", "José")
        ),
        Service(
            idServico = 3,
            nomeServico = "Corte e Barba",
            duracaoServico = LocalTime.of(0, 50),
            descricaoServico = "Pacote com corte de cabelo e barba",
            precoServico = "R$ 50,00",
            proficional = listOf("Lucas")
        ),
        Service(
            idServico = 4,
            nomeServico = "Sobrancelha Masculina",
            duracaoServico = LocalTime.of(0, 15),
            descricaoServico = "Design e limpeza da sobrancelha",
            precoServico = "R$ 15,00",
            proficional = listOf("Marcos")
        ),
        Service(
            idServico = 5,
            nomeServico = "Hidratação Capilar",
            duracaoServico = LocalTime.of(0, 30),
            descricaoServico = "Tratamento para hidratação profunda dos fios",
            precoServico = "R$ 40,00",
            proficional = listOf("José")
        ),
        Service(
            idServico = 6,
            nomeServico = "Massagem Capilar",
            duracaoServico = LocalTime.of(0, 20),
            descricaoServico = "Massagem relaxante no couro cabeludo",
            precoServico = "R$ 20,00",
            proficional = listOf("João", "Pedro", "Lucas")
        )
    ),
    proficionais = initProfessionals()
)

fun initProfessionals() = listOf(
    "João",
    "Pedro",
    "Lucas",
    "Marcos",
    "José"
)



@Preview(showBackground = true)
@Composable
private fun CardServicePreview() {
    CardService(
        titleService = "Corte de Cabelo",
        professionalName = "João",
        valueService = "R$ 30,00",
        timeService = "30 min"
    )
}