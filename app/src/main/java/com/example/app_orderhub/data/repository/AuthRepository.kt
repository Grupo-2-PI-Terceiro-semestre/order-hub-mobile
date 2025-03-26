package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.auth.LoginRequest
import com.example.app_orderhub.data.model.auth.LoginResponse
import com.example.app_orderhub.data.model.auth.RegisterRequest
import com.example.app_orderhub.data.remote.AuthApi
import com.example.app_orderhub.domain.model.Client

class AuthRepository(private val authApi: AuthApi) {

    suspend fun login(client: Client): LoginResponse {
        return authApi.login(
            LoginRequest(
                client.emailPessoa,
                client.senha
            )
        )
    }

    suspend fun register(client: Client): LoginResponse {
        return authApi.register(
            RegisterRequest(
                client.nomePessoa,
                client.emailPessoa,
                client.numeroTelefone,
                client.senha
            )
        )
    }
}