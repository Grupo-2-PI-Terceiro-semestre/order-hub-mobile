package com.example.app_orderhub.di

import com.example.app_orderhub.data.remote.AuthApi
import com.example.app_orderhub.data.remote.CatalogApi
import com.example.app_orderhub.data.remote.HomeApi
import com.example.app_orderhub.data.remote.config.RetrofitClient
import com.example.app_orderhub.data.remote.config.ScheduleApi
import com.example.app_orderhub.data.remote.config.SearchApi
import com.example.app_orderhub.data.repository.AuthRepository
import com.example.app_orderhub.data.repository.CatalogRepository
import com.example.app_orderhub.data.repository.HomeRepository
import com.example.app_orderhub.data.repository.ScheduleRepository
import com.example.app_orderhub.data.repository.SearchRepository

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
    private val searchApi: SearchApi = RetrofitClient.createApi(SearchApi::class.java)

    // Cria a instância do Repository
    val searchRepository: SearchRepository = SearchRepository(searchApi)

    // Cria a instância da API
    private val scheduleApi: ScheduleApi = RetrofitClient.createApi(ScheduleApi::class.java)

    // Cria a instância do Repository
    val scheduleRepository: ScheduleRepository = ScheduleRepository(scheduleApi)
}
