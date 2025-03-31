package com.example.app_orderhub.data.remote

import com.example.app_orderhub.data.model.map.NominatimResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleApiService {
    @GET("search?format=json")
    suspend fun getGeocode(
        @Query("q") address: String
    ): List<NominatimResponse>

    companion object {
        private const val BASE_URL = "https://nominatim.openstreetmap.org/"

        val instance: GoogleApiService by lazy {
            // Configura o interceptor para logar as requisições
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            // Cria o cliente HTTP com o interceptor
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            // Configura o Retrofit com o cliente HTTP personalizado
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client) // Adiciona o cliente HTTP com o interceptor
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
                .create(GoogleApiService::class.java)
        }
    }
}