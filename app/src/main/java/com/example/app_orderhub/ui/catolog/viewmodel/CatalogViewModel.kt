package com.example.app_orderhub.ui.catolog.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.catalog.ScheduleRequest
import com.example.app_orderhub.data.model.catalog.toEnterprise
import com.example.app_orderhub.di.AppModule
import com.example.app_orderhub.domain.model.Enterprise
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
                val enterprise = response.toEnterprise(idEnterprise.value?.toInt() ?: 0)
                _enterprise.value = enterprise
                onSuccess(enterprise)
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getTimes(
        idEnterprise: String,
        idService: String,
        idProfessional: String,
        date: String,
        onSuccess: (List<String>) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val formatter = DateTimeFormatter.ofPattern("d/M/yyyy")
                val localDate = LocalDate.parse(date, formatter)

                val response =
                    catalogRepository.getTimes(idEnterprise, idService, idProfessional, localDate)

                val times = response.map { it }
                onSuccess(times)
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createSchedule(
        idService: String,
        idProfessional: String,
        date: String,
        time: String,
        idClient: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm:ss")
                val schedule = LocalDateTime.parse("$date $time", inputFormatter)

                val outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
                val scheduleFormatted = schedule.format(outputFormatter)


                val request = ScheduleRequest(
                    idCliente = idClient,
                    idServico = idService.toInt(),
                    idProfissional = idProfessional.toInt(),
                    dataAgendamento = scheduleFormatted,
                    statusAgendamento = "PENDENTE"
                )

                catalogRepository.createSchedule(request)
                onSuccess()
            } catch (e: Exception) {
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}