package com.example.app_orderhub.data.model.schedule

import com.example.app_orderhub.domain.model.Professional

data class ScheduleResponse(
    val idAgendamento: Int,
    val nomeServico: String,
    val idServico: Int,
    val valorServico: String,
    val nomeEmpresa: String,
    val idEmpresa: Int,
    val dataHora: String,
    val status: String,
    val atendente: String,
    val urlImage : String? = null,
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
        valorServico = valorServico,
        nomeEmpresa = nomeEmpresa,
        idEmpresa = idEmpresa,
        dataHora = dataHora,
        status = status,
        atendente = atendente,
        urlImage = urlImage,
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
    val valorServico: String,
    val nomeEmpresa: String,
    val idEmpresa: Int,
    val dataHora: String,
    val status: String,
    val urlImage: String?,
    val atendente: String,
    val profissionais: List<Professional>
){
    constructor(): this(
        idAgendamento = 0,
        nomeServico = "",
        idServico = 0,
        valorServico = "",
        nomeEmpresa = "",
        idEmpresa = 0,
        dataHora = "",
        status = "",
        urlImage = "",
        atendente = "",
        profissionais = emptyList()
    )
}




data class ProfessionalResponse(
    val idUsuario: Int,
    val nomePessoa: String
)