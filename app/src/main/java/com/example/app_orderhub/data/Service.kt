package com.example.app_orderhub.data

import java.time.LocalTime

data class Service(
    val idServico: Int,
    val nomeServico: String,
    val duracaoServico: LocalTime,
    val descricaoServico: String,
    val precoServico: String,
    val proficional: List<String>
) {
    constructor(
        idServico: Int,
        nomeServico: String,
        duracaoServico: LocalTime,
        descricaoServico: String
    ) : this(
        idServico = idServico,
        nomeServico = nomeServico,
        duracaoServico = duracaoServico,
        descricaoServico = descricaoServico,
        precoServico = "",
        proficional = listOf()
    )

    constructor(
    ) : this(
        idServico = 0,
        nomeServico = "",
        duracaoServico = LocalTime.now(),
        descricaoServico = "",
        precoServico = "",
        proficional = listOf()
    )
}
