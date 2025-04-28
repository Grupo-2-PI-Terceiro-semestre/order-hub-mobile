package com.example.app_orderhub.ui.map.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.remote.GoogleApiService
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Address
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

    private val catalogRepository = AppModule.catalogRepository


    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> = _userLocation

    private val _locations = MutableStateFlow<List<LatLng>>(emptyList())
    val locations: StateFlow<List<LatLng>> = _locations

    private val _name = MutableStateFlow<List<String?>>(emptyList())
    val name: StateFlow<List<String?>> = _name

    private val _urlImage = MutableStateFlow<String?>(null)
    val urlImage: StateFlow<String?> = _urlImage

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

    @SuppressLint("MissingPermission")
    fun getUserLocationAndFetchLocations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                )
                result.addOnSuccessListener { location ->
                    location?.let {
                        _userLocation.value = LatLng(it.latitude, it.longitude)
                        Log.d("MapViewModel", "Localização do usuário: ${it.latitude}, ${it.longitude}")

                        // Agora faz o Reverse Geocoding para pegar a cidade
                        fetchCityAndLocations(it.latitude, it.longitude)
                    }
                }
            } catch (e: Exception) {
                Log.e("MapViewModel", "Erro ao obter localização do usuário: ${e.message}")
            }
        }
    }

    private fun fetchCityAndLocations(latitude: Double, longitude: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = GoogleApiService.instance.getReverseGeocode(latitude, longitude)

                val addressJson = response.getAsJsonObject("address")
                val city = addressJson.get("city")?.asString
                    ?: addressJson.get("town")?.asString
                    ?: addressJson.get("village")?.asString

                val suburb = addressJson.get("suburb")?.asString

                city?.let {
                    fetchLocationsFromCity(it, suburb ?: "")
                }

            } catch (e: Exception) {
                Log.e("MapViewModel", "Erro ao buscar cidade: ${e.message}")
            }
        }
    }

    private fun fetchLocationsFromCity(city: String, suburb: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val locationsResponse = catalogRepository.getEnterprisesForLocation(city,suburb)
                
                val newLocations = locationsResponse.map { LatLng(it.endereco.lat.toDouble(), it.endereco.lng.toDouble()) }

                val newNames = locationsResponse.map { it.nomeEmpresa }

                _locations.value = newLocations
                _name.value = newNames

            } catch (e: Exception) {
                Log.e("MapViewModel", "Erro ao buscar locais da cidade: ${e.message}")
            }
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


