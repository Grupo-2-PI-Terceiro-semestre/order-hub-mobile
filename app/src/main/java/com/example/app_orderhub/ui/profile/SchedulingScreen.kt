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
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.catolog.components.ImageEnterprise
import com.example.app_orderhub.ui.components.CardCurrent
import com.example.app_orderhub.ui.components.CardPast
import com.example.app_orderhub.ui.profile.viewmodel.ProfileViewModel
import com.example.app_orderhub.ui.search.viewmodel.ScheduleViewModel
import com.example.app_orderhub.util.theme.ColorBackGroundDefault

@Composable
fun SchedulingScreen(
    navController: NavController
) {
    MenuNavigation(navController) {
       ContentScheduling(idClient = "11" )
    }
}

@Composable
fun ContentScheduling(scheduleViewModel: ScheduleViewModel = viewModel(),
                      idClient: String) {

    val schedulesState  = scheduleViewModel.schedules.collectAsState()

    LaunchedEffect(Unit) {
        scheduleViewModel.onParamChanged(idClient)
    }

    LaunchedEffect(Unit) {
        scheduleViewModel.getSchedule(
            onSuccess = {
                print("Sucesso ao carregar agendamentos")
            },
            onError = {
                print("Erro ao carregar agendamentos")
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


//        CardCurrent(
//            serviceName = "Corte e Barba",
//            professionalName = "Com Kevin Silva",
//            date = "Março",
//            day = "15",
//            time = "10:00",
//            onCancel = {},
//            onReschedule = {},
//            modifier = Modifier.padding(horizontal = 20.dp)
//        )

        Text(
            text = "Reservas Finalizadas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))




//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(ColorBackGroundDefault)
//                .verticalScroll(rememberScrollState()),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {


//        schedules.value?.forEach { schedule ->
//            CardPast(schedule = schedule)
//            Spacer(modifier = Modifier.height(16.dp))
//        }

        schedulesState.value?.let { scheduleList ->
            scheduleList.forEach { schedule ->
                CardPast(schedule = schedule)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }


//            schedules.value?.let {
//                CardPast(
//                    schedule = it
//                )
//            }
//        }
//        CardPast(
//            date = "Sab, 13 junho 2024",
//            serviceName = "Corte e Barba",
//            professionalName = "Com Kevin Silva",
//            status = "FINALIZADA",
//            onReschedule = {},
//            modifier = Modifier.padding(horizontal = 20.dp)
//        )

//        Spacer(modifier = Modifier.height(32.dp))

    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSchedulingScreen() {
//    SchedulingScreen(navController = rememberNavController())
//}
