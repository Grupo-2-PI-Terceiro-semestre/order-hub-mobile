package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.home.EnterpriseResponse
import com.example.app_orderhub.data.remote.HomeApi

class HomeRepository(private val homeApi: HomeApi) {

    suspend fun getEnterprise(param: String): List<EnterpriseResponse> {
        return homeApi.getEnterprises(param)
    }

    suspend fun getEnterpriseForEnterprise(category: String): List<EnterpriseResponse> {
        return homeApi.getEnterprisesForCategory(category)
    }

}