package com.example.app_orderhub.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_orderhub.R
import com.example.app_orderhub.data.model.schedule.ScheduleDTO
import com.example.app_orderhub.domain.model.Service
import com.example.app_orderhub.ui.catolog.components.ScheduleModal
import com.example.app_orderhub.ui.search.viewmodel.ScheduleViewModel
import com.example.app_orderhub.util.components.ConfirmActionModal
import com.example.app_orderhub.viewmodel.SharedClientViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CardCurrent(
    onCancel: () -> Unit = {},
    onReschedule: () -> Unit = {},
    modifier: Modifier = Modifier,
    schedule: ScheduleDTO = ScheduleDTO(),
//    idAgendamento: String,
    scheduleViewModel: ScheduleViewModel,
    sharedClientViewModel : SharedClientViewModel,
    navController: NavController

    ) {

    val context = androidx.compose.ui.platform.LocalContext.current
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val dateTime = try {
        LocalDateTime.parse(schedule.dataHora, formatter)
    } catch (e: Exception) {
        null
    }

    val month = dateTime?.month?.getDisplayName(java.time.format.TextStyle.FULL, Locale("pt", "BR")) ?: ""
    val day = dateTime?.dayOfMonth?.toString() ?: ""
    val time = dateTime?.toLocalTime()?.toString()?.substring(0, 5) ?: ""

    var showModal by remember { mutableStateOf(false) }
    val warningIcon = painterResource(id = R.drawable.canceled)

    var showSchedule by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(8.dp), // Adicionando sombra
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${schedule.nomeServico}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "${schedule.atendente}",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = month,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Text(
                        text = day,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = time,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
            }

            IconProfile(
                title = "Barbearia NK",
                textColor = Color.Black,
                fontSize = 16,
                size = 40
            )
            Text(
                text = "${schedule.status}",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {showModal = true},
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Red),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Cancelar", color = Color.Red)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { showSchedule = true },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = "Alterar", color = Color.Black)
                }
            }

            if (showModal) {
                val context = LocalContext.current

                ConfirmActionModal(
                    title = "Confirmar cancelamento",
                    type = "Cancel",
                    icon = warningIcon,
                    message = "Você tem certeza que deseja cancelar?",
                    onConfirm = {
                        showModal = false
                        scheduleViewModel.deleteSchedule(
                            scheduleId = schedule.idAgendamento.toString(),
                            onSuccess = {
                                Toast.makeText(context, "Agendamento deletado com sucesso!", Toast.LENGTH_SHORT).show()
                            },
                            onError = { errorMsg ->
                                Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    onCancel = { showModal = false }
                )

            }

            if (showSchedule) {
                val context = LocalContext.current

                val service = Service(
                    idServico = schedule.idServico,
                    nomeServico = schedule.nomeServico ?: "",
                    duracaoServico = "",
                    descricaoServico = "",
                    precoServico = 0.0,
                    proficional = listOf(schedule.atendente ?: "")
                )

                ScheduleModal(
                    onDismiss = { showSchedule = false },
                    schedule.idEmpresa.toString(),
                    schedule.idAgendamento,
                    service,
                    schedule.profissionais,
                    schedule.dataHora,
                    sharedClientViewModel,
                    navController
                )
            }
        }
    }
}