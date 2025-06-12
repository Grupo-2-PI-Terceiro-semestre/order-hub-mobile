package com.example.app_orderhub.data.model.schedule

import com.example.app_orderhub.domain.model.Professional

data class ScheduleResponse(
    val idAgendamento: Int,
    val nomeServico: String,
    val idServico: Int,
    val nomeEmpresa: String,
    val idEmpresa: Int,
    val dataHora: String,
    val status: String,
    val atendente: String,
    val proficionaisDaEmpresa: List<ProfessionalResponse>
)
fun List<ScheduleResponse>.toSchedules(): List<ScheduleDTO> {
    return this.map { it.toSchedule() }
}

fun ScheduleResponse.toSchedule(): ScheduleDTO {
    return ScheduleDTO(
        idAgendamento = idAgendamento,
        nomeServico = nomeServico,
        idServico = idServico,
        nomeEmpresa = nomeEmpresa,
        idEmpresa = idEmpresa,
        dataHora = dataHora,
        status = status,
        atendente = atendente,
        profissionais = proficionaisDaEmpresa.map {
            Professional(
                idUsuario = it.idUsuario,
                nomePessoa = it.nomePessoa
            )
        }
    )
}

data class ScheduleDTO(
    val idAgendamento: Int,
    val nomeServico: String,
    val idServico: Int,
    val nomeEmpresa: String,
    val idEmpresa: Int,
    val dataHora: String,
    val status: String,
    val atendente: String,
    val profissionais: List<Professional>
){
    constructor(): this(
        idAgendamento = 0,
        nomeServico = "",
        idServico = 0,
        nomeEmpresa = "",
        idEmpresa = 0,
        dataHora = "",
        status = "",
        atendente = "",
        profissionais = emptyList()
    )
}




data class ProfessionalResponse(
    val idUsuario: Int,
    val nomePessoa: String
)