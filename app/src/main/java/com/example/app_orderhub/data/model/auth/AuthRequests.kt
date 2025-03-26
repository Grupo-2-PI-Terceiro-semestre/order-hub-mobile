package com.example.app_orderhub.data.model.auth

data class LoginRequest(
    val email: String,
    val senha: String
)

data class RegisterRequest(
    val nomePessoa: String,
    val emailPessoa: String,
    val numeroTelefone: String,
    val senha: String
)

