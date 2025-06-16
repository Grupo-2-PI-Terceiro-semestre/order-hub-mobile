package com.example.app_orderhub.data.model.catalog

data class ScheduleRequest(
    val idAgendamento: String,
    val idCliente: Int,
    val idServico: Int,
    val idProfissional: Int,
    val dataAgendamento: String,
    val statusAgendamento: String? = null
)