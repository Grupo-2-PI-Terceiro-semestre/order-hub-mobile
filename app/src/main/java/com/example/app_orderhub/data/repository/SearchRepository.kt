package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.home.EnterpriseResponse
import com.example.app_orderhub.data.remote.config.SearchApi

class SearchRepository (private val searchApi: SearchApi) {

    suspend fun getEnterprise(param: String): List<EnterpriseResponse> {
        return searchApi.getEnterprises(param)
    }
}