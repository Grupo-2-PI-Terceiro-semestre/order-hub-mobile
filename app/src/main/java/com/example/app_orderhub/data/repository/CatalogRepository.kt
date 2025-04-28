package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.catalog.CatalogResponse
import com.example.app_orderhub.data.remote.CatalogApi

class CatalogRepository(private val catalogApi: CatalogApi) {

    suspend fun getEnterprise(idEnterprise: String): CatalogResponse {
        return catalogApi.getEnterprise(idEnterprise)
    }

    suspend fun getEnterprisesForLocation(city: String, suburb: String): List<CatalogResponse> {
        return catalogApi.getEnterprisesForLocation(city, suburb)
    }
}