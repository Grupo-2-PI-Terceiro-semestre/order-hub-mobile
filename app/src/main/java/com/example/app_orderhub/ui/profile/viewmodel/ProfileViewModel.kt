package com.example.app_orderhub.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.client.toClient
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val clientRepository = AppModule.clientRepository

    private val _idClient = MutableStateFlow<String?>("")
    val idClient: StateFlow<String?> = _idClient

    private val _client = MutableStateFlow<Client>(Client())
    val client: StateFlow<Client> = _client

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onIdClientChanged(newValue: String) {
        _idClient.value = newValue
    }

    fun getClient(onSuccess: (Client) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = clientRepository.getClient(_idClient.value ?: "")
                val client = response.toClient()
                _client.value = client
                onSuccess(client)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}