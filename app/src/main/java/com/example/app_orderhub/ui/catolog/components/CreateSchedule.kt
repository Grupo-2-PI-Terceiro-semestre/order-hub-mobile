package com.example.app_orderhub.ui.catolog.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_orderhub.R
import com.example.app_orderhub.domain.model.Professional
import com.example.app_orderhub.domain.model.Service
import com.example.app_orderhub.ui.auth.components.ButtonAuth
import com.example.app_orderhub.ui.catolog.viewmodel.CatalogViewModel
import com.example.app_orderhub.ui.profile.viewmodel.ProfileViewModel
import com.example.app_orderhub.util.components.ConfirmActionModal
import com.example.app_orderhub.util.theme.OrderHubBlue
import com.example.app_orderhub.viewmodel.SharedClientViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleModal(
    onDismiss: () -> Unit,
    idEnterprise: String,
    idAgendamento: String?,
    idClient: String?,
    service: Service,
    professional: List<Professional>,
    dataHora: String? = null,
    sharedClientViewModel : SharedClientViewModel,
    navController: NavController
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
//    val idClient = sharedClientViewModel.client.collectAsState().value?.idPessoa

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
            SchedulePreview(service, professional, idEnterprise, idClient, idAgendamento, dataHora, onDismiss, navController)
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SchedulePreview(
    service: Service,
    professional: List<Professional>,
    idEnterprise: String,
    idClient: String?,
    idAgendamento: String?,
    dataHora: String?,
    onDismiss: () -> Unit,
    navController: NavController,
) {
    var selectedItem by remember { mutableStateOf<Professional?>(null) }
    var selectedDate by remember { mutableStateOf<String?>(null) }
    var appointmentTimes by remember { mutableStateOf<List<String>>(emptyList()) }
    val selectedTime = remember { mutableStateOf<String?>(null) }
    val showConfirmationModal = remember { mutableStateOf(false) }
    val agendamentoSelecionado = remember {
        mutableStateOf(AgendamentoSelecionado())
    }

    LaunchedEffect(Unit) {
        dataHora?.let {
            val localDateTime = LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            val dateOnly = localDateTime.toLocalDate().toString() // Resultado: "2025-06-12"

            selectedDate = dateOnly
            agendamentoSelecionado.value = agendamentoSelecionado.value.copy(data = dateOnly)
        }
    }

    val context = LocalContext.current
    val viewModel: CatalogViewModel = viewModel()

    val localDateFromDataHora = remember(dataHora) {
        dataHora?.let {
            val localDateTime = LocalDateTime.parse(it, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            localDateTime.toLocalDate().toString() // "2025-06-12"
        }
    }

    LaunchedEffect(selectedItem, selectedDate) {
        val professionalId = selectedItem?.idUsuario
        val date = selectedDate

        if (professionalId != null && !date.isNullOrEmpty()) {
            viewModel.getTimes(
                idEnterprise = idEnterprise,
                idService = service.idServico.toString(),
                idProfessional = professionalId.toString(),
                date = date,
                onSuccess = { times ->
                    appointmentTimes = times
                },
                onError = {
                    Toast.makeText(context, "Erro: $it", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = service.nomeServico,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Calendar(initialDate = localDateFromDataHora) { selected ->
            selectedDate = selected

            agendamentoSelecionado.value = agendamentoSelecionado.value.copy(data = selected)
        }

        SelectableDropdown(options = professional, selectedOption = "Selecione um profissional") {
            selectedItem = it
            agendamentoSelecionado.value = agendamentoSelecionado.value.copy(profissional = it)
        }

        Spacer(modifier = Modifier.height(40.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(appointmentTimes) { time ->
                Appointment(
                    time = time,
                    isSelected = selectedTime.value == time,
                    onClick = {
                        selectedTime.value = time
                        agendamentoSelecionado.value =
                            agendamentoSelecionado.value.copy(horario = time)
                    }

                )
            }
        }


        Spacer(modifier = Modifier.height(20.dp))

        ServiceDetail(service, agendamentoSelecionado.value)

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Pode adicionar mais info se quiser
            }

            ButtonAuth(
                text = "Confirmar",
                borderColor = OrderHubBlue,
                borderRadius = 10,
                height = 45,
                fontSize = 22,
                width = 1.0f,
                onClick = {
                    viewModel.createSchedule(
                        idAgendamento = idAgendamento?: "",
                        idClient = idClient ?: "",
                        idService = service.idServico.toString(),
                        idProfessional = selectedItem?.idUsuario.toString(),
                        date = agendamentoSelecionado.value.data.toString(),
                        time = agendamentoSelecionado.value.horario.toString(),
                        onSuccess = {
                            showConfirmationModal.value = true
                        },
                        onError = {
                            Toast.makeText(context, "Erro: $it", Toast.LENGTH_SHORT).show()
                        }
                    )
                },
            )
            if(showConfirmationModal.value){
                ConfirmActionModal(
                    type = "Confirm",
                    title = "Agendamento realizado!",
                    icon = painterResource(id = R.drawable.checked),
                    message = messageAgendamento(
                        agendamentoSelecionado.value.data.toString(),
                        agendamentoSelecionado.value.horario.toString()
                    ),
                    onConfirm = {
                        navController.navigate("scheduling"){
                            popUpTo("catalog/{idEnterprise}") {
                                inclusive = true
                            }
                        }
                        onDismiss()
                    },
                    onCancel = {
                        showConfirmationModal.value = false
                        onDismiss()
                    }
                )
            }


        }

        Spacer(modifier = Modifier.height(60.dp))
    }
}

fun messageAgendamento(date: String, time: String): String {
    return "Agendamento Confirmado para o dia $date às $time"
}

