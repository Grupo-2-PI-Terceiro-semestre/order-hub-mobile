package com.example.app_orderhub.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.auth.toClient
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AuthViewModel() : ViewModel() {

    private val authRepository = AppModule.authRepository

    private val _client = MutableStateFlow(Client())
    val client: StateFlow<Client> = _client

    private val _token = MutableStateFlow<String>("")
    val token: StateFlow<String> = _token

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

    fun onTokenChanged(newToken: String) {
        _token.value = newToken
    }

    fun onMessageChanged(newMessage: String) {
        _errorMessage.value = newMessage
    }

    fun login(onSuccess: (Client) -> Unit, onError: (String) -> Unit) {
        _isLoading.value = true

        if(_client.value.emailPessoa.isEmpty() || _client.value.senha.isEmpty()) {
            _errorMessage.value = "Email e senha são obrigatórios"
            onError("Email e senha são obrigatórios")
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            _errorMessage.value = null


            try {
                val response = authRepository.login(_client.value)
                val client = response.toClient()
                onSuccess(client)
            } catch (e: HttpException) {
                when (e.code()) {
                    401 -> _errorMessage.value = "Email ou senha inválidos"
                    else -> _errorMessage.value = "Erro de servidor: ${e.message()}"
                }
                onError(e.message())
            } catch (e: IOException) {
                _errorMessage.value = "Falha de conexão. Verifique sua internet."
                onError(e.message ?: "Erro de rede")
            } catch (e: Exception) {
                _errorMessage.value = "Erro inesperado: ${e.message}"
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun register(onSuccess: (Client) -> Unit, onError: (String) -> Unit) {

        _isLoading.value = true

        if(_client.value.nomePessoa.isEmpty() || _client.value.numeroTelefone.isEmpty() || _client.value.emailPessoa.isEmpty() || _client.value.senha.isEmpty()) {
            _errorMessage.value = "Nome, telefone, email e senha são obrigatórios"
            onError("Nome, telefone, email e senha são obrigatórios")
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            _errorMessage.value = null

            try {
                val response = authRepository.register(_client.value)
                val client = response.toClient()
                onSuccess(client)
            } catch (e: Exception) {
                if(e is HttpException) {
                    when (e.code()) {
                        400 -> _errorMessage.value = "Dados inválidos"
                        409 -> _errorMessage.value = "Email já cadastrado"
                        else -> _errorMessage.value = "Erro de servidor: ${e.message()}"
                    }
                } else if (e is IOException) {
                    _errorMessage.value = "Falha de conexão. Verifique sua internet."
                } else {
                    _errorMessage.value = "Erro inesperado: ${e.message}"
                }
                onError(_errorMessage.value.toString())
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun sendEmail(onSuccess: (String) -> Unit, onError: (String) -> Unit) {

        _isLoading.value = true

        if(_client.value.emailPessoa.isEmpty()) {
            _errorMessage.value = "Email é obrigatório"
            onError("O email é obrigatório")
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            _errorMessage.value = null

            try {
                val response = authRepository.sendEmail(_client.value.emailPessoa)
                val token = response.toString()
                onSuccess(token)
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun validate(onSuccess: (Boolean) -> Unit, onError: (String) -> Unit) {

        _isLoading.value = true

        if(_token.value.isEmpty()) {
            _errorMessage.value = "Token é obrigatório"
            onError("O token é obrigatório")
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            _errorMessage.value = null

            try {
                val response = authRepository.validate(_token.value)
                val tokenIsValid = response
                if(!tokenIsValid) {
                    _errorMessage.value = "Token inválido"
                    onError("Token inválido")
                    _isLoading.value = false
                    return@launch
                }
                onTokenChanged(_token.value)
                onSuccess(tokenIsValid)
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun resetPassword(onSuccess: (Unit) -> Unit, onError: (String) -> Unit) {

        _isLoading.value = true

        if(_client.value.senha.isEmpty() || _token.value.isEmpty()) {
            _errorMessage.value = "Senha é obrigatória"
            onError("Senha é obrigatória")
            _isLoading.value = false
            return
        }

        viewModelScope.launch {
            _errorMessage.value = null

            try {
                val response = authRepository.resetPassword(_token.value, _client.value.senha)
                if (response.isSuccessful) {
                    onSuccess(Unit)
                } else {
                    onError("Erro ao redefinir senha: ${response.code()}")
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
