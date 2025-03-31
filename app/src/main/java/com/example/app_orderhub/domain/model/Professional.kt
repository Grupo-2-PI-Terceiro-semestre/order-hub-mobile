package com.example.app_orderhub.domain.model

data class Professional(
    val idUsuario: Int,
    val nomePessoa: String,
) {
    constructor() : this(
        idUsuario = 0,
        nomePessoa = ""
    )

    fun toDomainModel(): Professional {
        return copy(idUsuario, nomePessoa)
    }
}