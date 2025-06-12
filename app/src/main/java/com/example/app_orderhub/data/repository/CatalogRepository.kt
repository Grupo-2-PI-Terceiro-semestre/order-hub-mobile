package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.catalog.CatalogResponse
import com.example.app_orderhub.data.model.catalog.ScheduleRequest
import com.example.app_orderhub.data.remote.CatalogApi
import java.time.LocalDate

class CatalogRepository(private val catalogApi: CatalogApi) {

    suspend fun getEnterprise(idEnterprise: String): CatalogResponse {
        return catalogApi.getEnterprise(idEnterprise)
    }

    suspend fun getEnterprisesForLocation(city: String, suburb: String): List<CatalogResponse> {
        return catalogApi.getEnterprisesForLocation(city, suburb)
    }

    suspend fun getTimes(
        idEnterprise: String,
        idService: String,
        idProfessional: String,
        date: LocalDate
    ): List<String> {
        return catalogApi.getTimes(idEnterprise, idService, idProfessional, date)
    }

    suspend fun createSchedule(request: ScheduleRequest): Boolean {
        val response = catalogApi.createSchedule(request)
        return response.isSuccessful
    }
}