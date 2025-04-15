package com.example.app_orderhub.ui.profile.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.client.ClientRequest
import com.example.app_orderhub.data.model.client.toClient
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class ProfileViewModel : ViewModel() {

    private val clientRepository = AppModule.clientRepository

    private val _idClient = MutableStateFlow<String>("")
    val idClient: StateFlow<String> = _idClient

    private val _client = MutableStateFlow<Client>(Client())
    val client: StateFlow<Client> = _client

    val clientName: String = _client.value.nomePessoa

    fun onNameChanged(newValue: String) {
        _client.value = _client.value.copy(nomePessoa = newValue)
    }

    fun onPhoneChanged(newValue: String) {
        _client.value = _client.value.copy(numeroTelefone = newValue)
    }

    fun onDateChanged(newValue: LocalDate) {
        _client.value = _client.value.copy(dataNascimento = newValue)
    }

    fun onGenderChanged(newValue: String) {
        _client.value = _client.value.copy(genero = newValue)
    }

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
                val response = clientRepository.getClient(_idClient.value)
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

    fun editClient(onSuccess: (Client) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {

                val response = clientRepository.editClient(
                    ClientRequest(
                        idPessoa = _idClient.value,
                        nomePessoa = _client.value.nomePessoa,
                        numeroTelefone = _client.value.numeroTelefone,
                        dataNascimento = _client.value.dataNascimento.toString(),
                        genero = _client.value.genero
                    )
                )
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