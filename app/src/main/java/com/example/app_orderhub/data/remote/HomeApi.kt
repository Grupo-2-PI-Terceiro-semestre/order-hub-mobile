package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.home.EnterpriseResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApi {

    @GET("empresas/buscar")
    suspend fun getEnterprises(@Query("termo") param: String): List<EnterpriseResponse>

    @GET("empresas/buscar/categoria/{categoria}")
    suspend fun getEnterprisesForCategory(@Path("categoria") category: String): List<EnterpriseResponse>

    // @GET("clientes/{id}")
    // suspend fun getCliente(@Path("id") id: Int): Cliente

    // @POST("clientes")
    // suspend fun createCliente(@Body request: Cliente): Cliente

    // @PUT("clientes/{id}")
    // suspend fun updateCliente(@Path("id") id: Int, @Body request: Cliente): Cliente

    // @DELETE("clientes/{id}")
    // suspend fun deleteCliente(@Path("id") id: Int): Cliente

}