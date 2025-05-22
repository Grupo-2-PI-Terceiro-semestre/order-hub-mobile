package com.example.app_orderhub.data.model.schedule

import com.example.app_orderhub.domain.model.Schedule
import java.time.LocalDateTime

data class ScheduleResponse(
    val idAgendamento : Int,
    val nomeServico: String,
    val nomeEmpresa: String,
    var dataHora: LocalDateTime,
    var status: String,
    var atendente: String
)

fun List<ScheduleResponse>.toSchedules(): List<Schedule> {
    return this.map { it.toSchedules() }
}

fun ScheduleResponse.toSchedules(): Schedule {
    return Schedule(
        idAgendamento,
        nomeServico,
        nomeEmpresa,
        dataHora,
        status,
        atendente,
    )
}