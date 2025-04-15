package com.example.app_orderhub.data.model.client

data class ClientRequest(
    val idPessoa: String,
    val nomePessoa: String,
    val numeroTelefone: String,
    val dataNascimento: String,
    val genero : String,
)