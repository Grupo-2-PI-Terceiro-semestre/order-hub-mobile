package com.example.app_orderhub.ui.map.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.remote.GoogleApiService
import com.example.app_orderhub.domain.model.Enterprise
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapViewModel(application: Application) : AndroidViewModel(application) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation

    private val _locations = MutableStateFlow<List<LatLng>>(emptyList())
    val locations: StateFlow<List<LatLng>> = _locations

    private val _name = MutableStateFlow<List<String?>>(emptyList())
    val name: StateFlow<List<String?>> = _name

    init {
        getUserLocation()
    }

    @SuppressLint("MissingPermission")
    fun getUserLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                )
                result.addOnSuccessListener { location: Location ->
                    location.let {
                        _userLocation.value = LatLng(it.latitude, it.longitude)
                        Log.d("MapViewModel", "Localização do usuário obtida: ${it.latitude}, ${it.longitude}")
                    }
                }
            } catch (e: Exception) {
                Log.e("MapViewModel", "Erro ao obter localização do usuário: ${e.message}")
            }
        }
    }

    fun setLocations(enterprises: List<State<Enterprise>>) {
        viewModelScope.launch(Dispatchers.IO) {
            val newLocations = mutableListOf<LatLng>()
            val newNames = mutableListOf<String>()

            val listEnterprises = enterprises.map { it.value }

            for (enterprise in listEnterprises) {
                try {
                    val fullAddress = "${enterprise.endereco.numero} ${enterprise.endereco.logradouro}, ${enterprise.endereco.cidade}, ${enterprise.endereco.uf}, ${enterprise.endereco.cep}, Brazil"
                    val response = GoogleApiService.instance.getGeocode(fullAddress)

                    if (response.isNotEmpty()) {
                        val location = response[0] // Primeiro resultado
                        newLocations.add(LatLng(location.lat.toDouble(), location.lon.toDouble()))
                        newNames.add(enterprise.nomeEmpresa)
                        Log.d("MapViewModel", "Localização encontrada: ${location.display_name}")
                    } else {
                        Log.d("MapViewModel", "Nenhum resultado encontrado para: $fullAddress")
                    }
                } catch (e: Exception) {
                    Log.e("MapViewModel", "Erro ao buscar localização: ${e.message}")
                }
            }
            _locations.value = newLocations
            _name.value = newNames
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun setLocation(enterprise: Enterprise) {
        viewModelScope.launch(Dispatchers.IO) {
            val newLocations = mutableListOf<LatLng>()
            val newNames = mutableListOf<String>()

                try {
                    val fullAddress = "${enterprise.endereco.numero} ${enterprise.endereco.logradouro}, ${enterprise.endereco.cidade}, ${enterprise.endereco.uf}, ${enterprise.endereco.cep}, Brazil"
                    val response = GoogleApiService.instance.getGeocode(fullAddress)

                    if (response.isNotEmpty()) {
                        val location = response[0] // Primeiro resultado
                        newLocations.add(LatLng(location.lat.toDouble(), location.lon.toDouble()))
                        newNames.add(enterprise.nomeEmpresa)
                        Log.d("MapViewModel", "Localização encontrada: ${location.display_name}")
                    } else {
                        Log.d("MapViewModel", "Nenhum resultado encontrado para: $fullAddress")
                    }
                } catch (e: Exception) {
                    Log.e("MapViewModel", "Erro ao buscar localização: ${e.message}")
                }

            _locations.value = newLocations
            _name.value = newNames
        }
    }

}


