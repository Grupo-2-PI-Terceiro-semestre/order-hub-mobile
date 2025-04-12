package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.client.ClientResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ClientApi {

    @GET("clientes/{idCliente}")
    suspend fun getClient(@Path("idCliente") idClient: String): ClientResponse
}