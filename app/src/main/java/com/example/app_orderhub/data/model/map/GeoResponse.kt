package com.example.app_orderhub.data.model.map

data class NominatimResponse(
    val place_id: Long,
    val lat: String,
    val lon: String,
    val display_name: String
)