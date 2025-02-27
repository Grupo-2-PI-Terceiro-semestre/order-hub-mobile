package com.example.app_orderhub.data

import java.time.LocalDate

data class Client(
    val nome: String,
    val sobrenome: String,
    val telefone: String,
    val dataNascimento: LocalDate,
    var email: String,
    val senha: String
) {
    // Construtor secundário (sobrecarga) para criar um cliente com apenas nome e e-mail
    constructor(nome: String, email: String) : this(
        nome = nome,
        sobrenome = "",
        telefone = "",
        dataNascimento = LocalDate.now(),
        email = email,
        senha = ""
    )

    // Construtor secundário (sobrecarga) para um cliente vazio
    constructor() : this(
        nome = "",
        sobrenome = "",
        telefone = "",
        dataNascimento = LocalDate.now(),
        email = "",
        senha = ""
    )
}
