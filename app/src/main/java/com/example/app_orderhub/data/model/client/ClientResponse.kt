package com.example.app_orderhub.data.model.client

import com.example.app_orderhub.domain.model.Client
import java.time.LocalDate

data class ClientResponse(
    val nomePessoa: String,
    val numeroTelefone: String,
    val dataNascimento: String,
    val genero : String,
)

fun ClientResponse.toClient(): Client {
    val data = dataNascimento.takeIf { it.isNotBlank() }?.let { LocalDate.parse(it) } ?: LocalDate.now()

    return Client(
        nomePessoa = nomePessoa,
        numeroTelefone = numeroTelefone,
        dataNascimento = data,
        genero = genero,
    )
}
