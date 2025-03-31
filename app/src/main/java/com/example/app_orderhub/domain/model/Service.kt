package com.example.app_orderhub.domain.model

import java.time.LocalTime

data class Service(
    val idServico: Int,
    val nomeServico: String,
    val duracaoServico: String,
    val descricaoServico: String,
    val precoServico: Double,
    val proficional: List<String>
) {
    constructor(
        idServico: Int,
        nomeServico: String,
        duracaoServico: String,
        descricaoServico: String,
        precoServico: Double
    ) : this(
        idServico = idServico,
        nomeServico = nomeServico,
        duracaoServico = duracaoServico,
        descricaoServico = descricaoServico,
        precoServico = precoServico,
        proficional = listOf()
    )

    constructor(
    ) : this(
        idServico = 0,
        nomeServico = "",
        duracaoServico = "",
        descricaoServico = "",
        precoServico = 0.0,
        proficional = listOf()
    )

    constructor(nomeServico: String) : this(
        idServico = 0,
        nomeServico = nomeServico,
        duracaoServico = "",
        descricaoServico = "",
        precoServico = 0.0,
        proficional = listOf()
    )

    fun toDomainModel(): Service {
        return copy(
            duracaoServico = try {
                LocalTime.parse(duracaoServico)
            } catch (e: Exception) {
                LocalTime.MIDNIGHT // Ou outra forma de tratamento de erro
            }.toString()
        )
    }
}
