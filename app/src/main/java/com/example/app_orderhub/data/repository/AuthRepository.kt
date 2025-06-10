package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.auth.LoginRequest
import com.example.app_orderhub.data.model.auth.LoginResponse
import com.example.app_orderhub.data.model.auth.RecoverPasswordRequest
import com.example.app_orderhub.data.model.auth.RegisterRequest
import com.example.app_orderhub.data.model.auth.ResetPasswordRequest
import com.example.app_orderhub.data.model.auth.ValidateTokenRequest
import com.example.app_orderhub.data.remote.AuthApi
import com.example.app_orderhub.domain.model.Client
import retrofit2.Response

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

    suspend fun sendEmail(email: String): String {
        return authApi.sendEmail(
            RecoverPasswordRequest(
                email
            )
        )
    }

    suspend fun validate(token: String) : Boolean {
        return authApi.validateToken(
            ValidateTokenRequest(
                token
            )
        )
    }

    suspend fun resetPassword(token: String, password: String) : Response<Unit>  {
        return authApi.resetPassword(
            ResetPasswordRequest(
                token,
                password
            )
        )
    }
}