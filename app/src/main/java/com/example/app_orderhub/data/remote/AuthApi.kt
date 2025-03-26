package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.auth.LoginRequest
import com.example.app_orderhub.data.model.auth.LoginResponse
import com.example.app_orderhub.data.model.auth.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("clientes/auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("clientes")
    suspend fun register(@Body request: RegisterRequest): LoginResponse

}
