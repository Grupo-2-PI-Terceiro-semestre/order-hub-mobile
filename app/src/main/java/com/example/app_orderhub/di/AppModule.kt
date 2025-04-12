package com.example.app_orderhub.di

import com.example.app_orderhub.data.remote.AuthApi
import com.example.app_orderhub.data.remote.CatalogApi
import com.example.app_orderhub.data.remote.ClientApi
import com.example.app_orderhub.data.remote.HomeApi
import com.example.app_orderhub.data.remote.config.RetrofitClient
import com.example.app_orderhub.data.repository.AuthRepository
import com.example.app_orderhub.data.repository.CatalogRepository
import com.example.app_orderhub.data.repository.ClientRepository
import com.example.app_orderhub.data.repository.HomeRepository

object AppModule {

    // Cria a instância da API
    private val authApi: AuthApi = RetrofitClient.createApi(AuthApi::class.java)

    // Cria a instância do Repository
    val authRepository: AuthRepository = AuthRepository(authApi)


    // Cria a instância da API
    private val homeApi: HomeApi = RetrofitClient.createApi(HomeApi::class.java)

    // Cria a instância do Repository
    val homeRepository: HomeRepository = HomeRepository(homeApi)


    // Cria a instância da API
    private val catalogApi: CatalogApi = RetrofitClient.createApi(CatalogApi::class.java)

    // Cria a instância do Repository
    val catalogRepository: CatalogRepository = CatalogRepository(catalogApi)


    // Cria a instância da API
    private val clientApi: ClientApi = RetrofitClient.createApi(ClientApi::class.java)

    val clientRepository: ClientRepository = ClientRepository(clientApi)
}
