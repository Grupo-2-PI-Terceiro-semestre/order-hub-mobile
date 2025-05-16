package com.example.app_orderhub.data.repository

import com.example.app_orderhub.data.model.home.EnterpriseResponse
import com.example.app_orderhub.data.model.schedule.ScheduleResponse
import com.example.app_orderhub.data.remote.config.ScheduleApi

class ScheduleRepository(private val scheduleApi: ScheduleApi) {
    suspend fun getSchedule(param: String): List<ScheduleResponse> {
        return scheduleApi.getSchedule(param)
    }
}