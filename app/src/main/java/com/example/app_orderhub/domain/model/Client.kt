package com.example.app_orderhub.domain.model

data class Client(
    val idPessoa : Int,
    val nomePessoa: String,
    val numeroTelefone: String,
    var emailPessoa: String,
    var senha: String
) {

    // Construtor secundário (sobrecarga) para criar um cliente com apenas nome e e-mail
    constructor(idPessoa: Int, nomePessoa: String, emailPessoa: String, numeroTelefone: String) : this(
        idPessoa = idPessoa,
        nomePessoa = nomePessoa,
        numeroTelefone = numeroTelefone,
        emailPessoa = emailPessoa,
        senha = ""
    )

    // Construtor secundário (sobrecarga) para um cliente vazio
    constructor() : this(
        idPessoa = 0,
        nomePessoa = "",
        numeroTelefone = "",
        emailPessoa = "",
        senha = ""
    )
}
