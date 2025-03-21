package com.example.app_orderhub.data.model.auth

import com.example.app_orderhub.domain.model.Client

data class LoginResponse(
    val idPessoa: String,
    val nomePessoa: String,
    val emailPessoa: String,
    val numeroTelefone : String
)

fun LoginResponse.toClient(): Client {
    return Client(
        idPessoa = this.idPessoa.toIntOrNull() ?: 0,
        nomePessoa = this.nomePessoa,
        numeroTelefone = this.numeroTelefone,
        emailPessoa = this.emailPessoa,
        senha = ""
    )
}