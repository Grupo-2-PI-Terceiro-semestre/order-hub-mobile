package com.example.app_orderhub.ui.catolog.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_orderhub.domain.model.Professional
import com.example.app_orderhub.domain.model.Service
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.util.theme.OrderHubBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleModal(onDismiss: () -> Unit, service: Service, professional: List<Professional>) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)

    ModalBottomSheet(
        containerColor = Color.White,
        onDismissRequest = { onDismiss() },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 2)
                .background(Color.White),
            verticalArrangement = Arrangement.Center
        ) {
            SchedulePreview(service, professional)
        }
    }
}



@Composable
fun SchedulePreview(service: Service, professional: List<Professional>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<Professional?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    )

    {

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            text = service.nomeServico,
            color = Color.Black
        )

        Spacer(
            modifier = Modifier
                .height(10.dp)
        )


        Calendar() // Se necessário, adicione .fillMaxWidth() no próprio Calendar

        SelectableDropdown(options = professional, selectedOption = "Selecione um profissional") {
            selectedItem = it
        }
        Spacer(
            modifier = Modifier
                .height(40.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Appointment()

            Appointment()

            Appointment()

            Appointment(backgroundApp = OrderHubBlue)
        }

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        ServiceDetail(service)

        Spacer(
            modifier = Modifier
                .height(40.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
//                .border(2.dp, Color.Red)
                .fillMaxWidth(0.9f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
//                .border(2.dp, Color.Blue)
            ) {
            }

            ButtonAuth(
                "Confirmar",
                borderColor = OrderHubBlue,
                borderRadius = 10,
                height = 45,
                fontSize = 22,
                width = 1.0f
            )
        }
        Spacer(
            modifier = Modifier
                .height(60.dp)
        )
    }
}