package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.catalog.CatalogResponse
import com.example.app_orderhub.data.model.catalog.ScheduleRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate

interface CatalogApi {

    @GET("empresas/perfil/{idEmpresa}")
    suspend fun getEnterprise(@Path("idEmpresa") idEnterprise: String): CatalogResponse

    @GET("empresas/geolocalizacao")
    suspend fun getEnterprisesForLocation(
        @Query("cidade") city: String,
        @Query("bairro") suburb: String
    ): List<CatalogResponse>

    @GET("agendas/horarios-indisponiveis/empresa/{idEmpresa}")
    suspend fun getTimes(
        @Path("idEmpresa") idEnterprise: String,
        @Query("idServico") city: String,
        @Query("idProfissional") suburb: String,
        @Query("data") date: LocalDate,

        ): List<String>

    @POST("agendamentos/app/cliente")
    suspend fun createSchedule(@Body request: ScheduleRequest): Response<Unit>

    @PUT("agendamentos")
    suspend fun updateSchedule(@Body request: ScheduleRequest): Response<Unit>
}