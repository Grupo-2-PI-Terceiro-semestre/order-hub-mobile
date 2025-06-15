package com.example.app_orderhub.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.app_orderhub.domain.model.Schedule
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.catolog.components.ImageEnterprise
import com.example.app_orderhub.ui.components.CardCurrent
import com.example.app_orderhub.ui.components.CardPast
import com.example.app_orderhub.ui.profile.viewmodel.ProfileViewModel
import com.example.app_orderhub.ui.search.viewmodel.ScheduleViewModel
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.example.app_orderhub.viewmodel.SharedClientViewModel

@Composable
fun SchedulingScreen(
    navController: NavController, sharedClientViewModel: SharedClientViewModel = viewModel()
) {
    val client = sharedClientViewModel.client.collectAsState().value

    MenuNavigation(navController) {
       ContentScheduling(idClient = client?.idPessoa.toString())
    }
}

@Composable
fun ContentScheduling(
    scheduleViewModel: ScheduleViewModel = viewModel(),
    idClient: String
) {
    val schedulesState = scheduleViewModel.schedules.collectAsState()

    LaunchedEffect(Unit) {
        scheduleViewModel.onParamChanged(idClient)
    }

    LaunchedEffect(Unit) {
        scheduleViewModel.getSchedule(
            onSuccess = {
                println("Sucesso ao carregar agendamentos")
            },
            onError = {
                println("Erro ao carregar agendamentos")
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF2196F3))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "MEUS AGENDAMENTOS",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        schedulesState.value?.let { scheduleList ->
            val agendamentosAtivos = scheduleList.filter {
                it.status == "PENDENTE" || it.status == "AGENDADO"
            }

            val agendamentosFinalizados = scheduleList.filter {
                it.status == "REALIZADO" || it.status == "CANCELADO"
            }

            if (agendamentosAtivos.isNotEmpty()) {
                Text(
                    text = "Agendamentos Ativos",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                agendamentosAtivos.forEach { schedule ->
                    CardCurrent(schedule = schedule, scheduleViewModel = scheduleViewModel)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            if (agendamentosFinalizados.isNotEmpty()) {
                Text(
                    text = "Histórico de Agendamentos",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))

                agendamentosFinalizados.forEach { schedule ->
                    CardPast(schedule = schedule, navController = navController)
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}