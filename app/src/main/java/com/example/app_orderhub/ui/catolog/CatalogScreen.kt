package com.example.app_orderhub.ui.catolog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.catolog.components.CardEnterprise
import com.example.app_orderhub.ui.catolog.components.ImageEnterprise
import com.example.app_orderhub.ui.catolog.viewmodel.CatalogViewModel
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.viewmodel.SharedClientViewModel


//@Preview
//@Composable
//private fun CatalogScreenPreview() {
//    CatalogScreen(rememberNavController())
//}

@Composable
fun CatalogScreen(
    idEnterprise: String,
    navController: NavController,
    catalogViewModel: CatalogViewModel = viewModel(),
    sharedClientViewModel: SharedClientViewModel
) {
    MenuNavigation(navController) {

        LaunchedEffect(Unit) {
            catalogViewModel.onIdEnterpriseChanged(idEnterprise.toString())
        }

        LaunchedEffect(Unit) {
            catalogViewModel.getProfileEnterprise(
                onSuccess = {},
                onError = { /* Pode exibir erro na tela se necessário */ }
            )
        }


        val enterprises = catalogViewModel.enterprise.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(ColorBackGroundDefault)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            enterprises.value?.let {
                val urlImages = if (it.imagens.isEmpty()) {
                    listOf("https://sintep.org.br/sintep/admin/uploads/arquivos/1634930642sem-foto.png")
                } else {
                    it.imagens
                }

                ImageEnterprise(
                    nameEnterprise = it.nomeEmpresa,
                    urlImages = urlImages
                )
            }
            enterprises.value?.let {
                CardEnterprise(
                    enterprise = it,
                    sharedClientViewModel = sharedClientViewModel,
                    navController = navController
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun CardServicePreview() {
//    CardService(
//        titleService = "Corte de Cabelo",
//        professionalName = "João",
//        valueService = "R$ 30,00",
//        timeService = "30 min"
//    )
//}