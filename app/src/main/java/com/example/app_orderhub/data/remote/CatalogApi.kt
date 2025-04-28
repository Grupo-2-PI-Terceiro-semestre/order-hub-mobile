package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.catalog.CatalogResponse
import com.example.app_orderhub.domain.model.Address
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CatalogApi {

    @GET("empresas/perfil/{idEmpresa}")
    suspend fun getEnterprise(@Path("idEmpresa") idEnterprise: String): CatalogResponse

    @GET("empresas/geolocalizacao")
    suspend fun getEnterprisesForLocation(
        @Query("cidade") city: String,
        @Query("bairro") suburb: String
    ): List<CatalogResponse>
}