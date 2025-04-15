package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.client.ClientRequest
import com.example.app_orderhub.data.model.client.ClientResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClientApi {

    @GET("clientes/{idCliente}")
    suspend fun getClient(@Path("idCliente") idClient: String): ClientResponse

    @PUT("clientes/atualizar-completo")
    suspend fun editClient(@Body clientRequest: ClientRequest): ClientResponse
}