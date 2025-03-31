package com.example.app_orderhub.di

import com.example.app_orderhub.data.remote.AuthApi
import com.example.app_orderhub.data.remote.config.RetrofitClient
import com.example.app_orderhub.data.repository.AuthRepository

object AppModule {

    // Cria a instância da API
    private val authApi: AuthApi = RetrofitClient.createApi(AuthApi::class.java)

    // Cria a instância do Repository
    val authRepository: AuthRepository = AuthRepository(authApi)
}
