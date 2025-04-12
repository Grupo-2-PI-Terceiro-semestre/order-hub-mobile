package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.client.ClientResponse
import com.example.app_orderhub.data.remote.ClientApi

class ClientRepository(private val clientApi: ClientApi) {


    suspend fun getClient(idClient: String): ClientResponse {
        return clientApi.getClient(idClient)
    }
}