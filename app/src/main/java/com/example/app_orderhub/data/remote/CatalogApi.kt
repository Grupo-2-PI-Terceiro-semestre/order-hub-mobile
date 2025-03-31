package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.catalog.CatalogResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CatalogApi {

    @GET("empresas/perfil/{idEmpresa}")
    suspend fun getEnterprise(@Path("idEmpresa") idEnterprise: String): CatalogResponse

}