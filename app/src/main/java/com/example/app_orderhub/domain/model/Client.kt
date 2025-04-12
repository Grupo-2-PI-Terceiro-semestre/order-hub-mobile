package com.example.app_orderhub.domain.model

import java.time.LocalDate

data class Client(
    val idPessoa : Int,
    val nomePessoa: String,
    val numeroTelefone: String,
    var dataNascimento: LocalDate,
    val genero: String,
    var emailPessoa: String,
    var senha: String
) {

    // Construtor secundário (sobrecarga) para criar um cliente com apenas nome e e-mail
    constructor(idPessoa: Int, nomePessoa: String, emailPessoa: String, numeroTelefone: String) : this(
        idPessoa = idPessoa,
        nomePessoa = nomePessoa,
        numeroTelefone = numeroTelefone,
        dataNascimento = LocalDate.now(),
        genero = "",
        emailPessoa = emailPessoa,
        senha = ""
    )

    constructor(nomePessoa: String, numeroTelefone: String, dataNascimento: LocalDate, genero: String,) : this(
        idPessoa = 0,
        nomePessoa = nomePessoa,
        numeroTelefone = numeroTelefone,
        dataNascimento = dataNascimento,
        genero = genero,
        emailPessoa = "",
        senha = ""
    )

    // Construtor secundário (sobrecarga) para criar um cliente com apenas nome e e-mail
    constructor(idPessoa: Int, nomePessoa: String, emailPessoa: String, numeroTelefone: String, dataNascimento: LocalDate, genero: String) : this(
        idPessoa = idPessoa,
        nomePessoa = nomePessoa,
        numeroTelefone = numeroTelefone,
        dataNascimento = dataNascimento,
        genero = genero,
        emailPessoa = emailPessoa,
        senha = ""
    )

    // Construtor secundário (sobrecarga) para um cliente vazio
    constructor() : this(
        idPessoa = 0,
        nomePessoa = "",
        numeroTelefone = "",
        dataNascimento = LocalDate.now(),
        genero = "",
        emailPessoa = "",
        senha = ""
    )
}
