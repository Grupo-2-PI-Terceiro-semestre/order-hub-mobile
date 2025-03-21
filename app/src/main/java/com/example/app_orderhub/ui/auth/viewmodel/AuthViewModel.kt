package com.example.app_orderhub.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.auth.toClient
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel() : ViewModel() {

    private val authRepository = AppModule.authRepository

    private val _client = MutableStateFlow(Client())
    val client: StateFlow<Client> = _client

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onEmailChanged(newEmail: String) {
        _client.value = _client.value.copy(emailPessoa = newEmail)
    }

    fun onPasswordChanged(newPassword: String) {
        _client.value = _client.value.copy(senha = newPassword)
    }

    fun onNameChanged(newName: String) {
        _client.value = _client.value.copy(nomePessoa = newName)
    }

    fun onPhoneChanged(newPhone: String) {
        _client.value = _client.value.copy(numeroTelefone = newPhone)
    }

    fun login(onSuccess: (Client) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = authRepository.login(_client.value)
                val client = response.toClient()
                onSuccess(client) // Chama o callback passando o objeto convertido
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(onSuccess: (Client) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = authRepository.register(_client.value)
                val client = response.toClient()
                onSuccess(client)
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
