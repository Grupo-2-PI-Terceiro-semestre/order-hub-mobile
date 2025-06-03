package com.example.app_orderhub.data.remote.config

import com.example.app_orderhub.data.model.schedule.ScheduleResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {
    @GET("agendamentos/cliente/{idCliente}")
    suspend fun getSchedule(@Path("idCliente") idCliente: String): List<ScheduleResponse>

    @PUT("agendamentos/cancelaAgendamento/{idAgendamento}")
    suspend fun deleteSchedule(@Path("idAgendamento") idAgendamento: String): ScheduleResponse
}