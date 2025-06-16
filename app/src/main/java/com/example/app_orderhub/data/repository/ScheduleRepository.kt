package com.example.app_orderhub.data.repository

import android.util.Log
import com.example.app_orderhub.data.model.home.EnterpriseResponse
import com.example.app_orderhub.data.model.schedule.ScheduleResponse
import com.example.app_orderhub.data.remote.config.ScheduleApi

class ScheduleRepository(private val scheduleApi: ScheduleApi) {
    suspend fun getSchedule(param: String): List<ScheduleResponse> {
        return scheduleApi.getSchedule(param)
    }

    suspend fun deleteSchedule(param: String): ScheduleResponse {
        Log.d("Repository", "Chamando deleteSchedule com id = $param")
        val response = scheduleApi.deleteSchedule(param)
        Log.d("Repository", "Resposta recebida: $response")
        return response
//        return scheduleApi.deleteSchedule(param)
    }
}