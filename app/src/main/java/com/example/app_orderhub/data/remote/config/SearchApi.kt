package com.example.app_orderhub.data.remote.config

import com.example.app_orderhub.data.model.home.EnterpriseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("empresas/buscar")
    suspend fun getEnterprises(@Query("termo") param: String): List<EnterpriseResponse>

}