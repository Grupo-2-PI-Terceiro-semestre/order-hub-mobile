package com.example.app_orderhub.domain.model

import java.time.LocalDateTime

data class Schedule(
    val idAgendamento : Int,
    val nomeServico: String,
    val nomeEmpresa: String,
    var dataHora: String,
    var status: String,
    var atendente: String
) {

//    constructor(idAgendamento: Int, nomeServico: String, nomeEmpresa: String, dataHora: LocalDateTime, status: String, atendente: String) : this(
//        idAgendamento = idAgendamento,
//        nomeServico = nomeServico,
//        nomeEmpresa = nomeEmpresa,
//        dataHora = dataHora,
//        status = status,
//        atendente = atendente
//    )

    constructor() :this(
        idAgendamento = 0,
        nomeServico = "",
        nomeEmpresa = "",
        dataHora = "",
        status = "",
        atendente = ""
    )
}