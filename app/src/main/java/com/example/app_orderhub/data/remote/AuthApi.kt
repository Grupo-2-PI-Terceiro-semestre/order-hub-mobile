package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.auth.LoginRequest
import com.example.app_orderhub.data.model.auth.LoginResponse
import com.example.app_orderhub.data.model.auth.RecoverPasswordRequest
import com.example.app_orderhub.data.model.auth.RegisterRequest
import com.example.app_orderhub.data.model.auth.ResetPasswordRequest
import com.example.app_orderhub.data.model.auth.ValidateTokenRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthApi {

    @POST("clientes/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("clientes")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

    @POST("password-reset/solicitar")
    suspend fun sendEmail(@Body request: RecoverPasswordRequest): String

    @POST("password-reset/validar")
    suspend fun validateToken(@Body request: ValidateTokenRequest): Boolean

    @PUT("password-reset/redefinir")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<Unit>
}
