package com.example.app_orderhub.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.home.toEnterprises
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Enterprise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchRepository = AppModule.searchRepository

    private val _param = MutableStateFlow<String?>("")
    val param: StateFlow<String?> = _param

    private val _categoryParam = MutableStateFlow<String?>("Barbearias")
    val categoryParam: StateFlow<String?> = _categoryParam

    private val _enterprises = MutableStateFlow<List<Enterprise>?>(null)
    val enterprises: StateFlow<List<Enterprise>?> = _enterprises

    private val _enterprisesForCategory = MutableStateFlow<List<Enterprise>?>(null)
    val enterprisesForCategory: StateFlow<List<Enterprise>?> = _enterprisesForCategory

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoadingCategory = MutableStateFlow(false)
    val isLoadingCategory: StateFlow<Boolean> = _isLoadingCategory

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onParamChanged(newParam: String) {
        _param.value = newParam
    }

    fun onParamWithCategoryChanged(newParam: String) {
        _categoryParam.value = newParam
    }


    fun getEnterprises(onSuccess: (List<Enterprise>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = searchRepository.getEnterprise(_param.value ?: "")
                val enterprises = response.toEnterprises()
                _enterprises.value = enterprises
                onSuccess(enterprises)
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
