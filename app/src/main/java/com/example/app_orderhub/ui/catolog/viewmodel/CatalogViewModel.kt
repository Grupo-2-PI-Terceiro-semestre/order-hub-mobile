package com.example.app_orderhub.ui.catolog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.catalog.toEnterprise
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Enterprise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogViewModel : ViewModel() {

    private val catalogRepository = AppModule.catalogRepository

    private val _idEnterprise = MutableStateFlow<String?>("")
    val idEnterprise: StateFlow<String?> = _idEnterprise

    private val _enterprise = MutableStateFlow<Enterprise?>(null)
    val enterprise: StateFlow<Enterprise?> = _enterprise

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun onIdEnterpriseChanged(newParam: String) {
        _idEnterprise.value = newParam
    }


    fun getProfileEnterprise(onSuccess: (Enterprise) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true

            try {
                val response = catalogRepository.getEnterprise(idEnterprise.value ?: "")
                val enterprise = response.toEnterprise()
                _enterprise.value = enterprise
                onSuccess(enterprise)
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

}