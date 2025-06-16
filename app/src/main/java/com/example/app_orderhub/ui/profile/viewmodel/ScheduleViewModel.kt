package com.example.app_orderhub.ui.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_orderhub.data.model.schedule.ScheduleDTO
import com.example.app_orderhub.data.model.schedule.toSchedules
import com.example.app_orderhub.di.AppModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel : ViewModel() {
    private val scheduleRepository = AppModule.scheduleRepository

    private val _param = MutableStateFlow<String?>("")
    val param: StateFlow<String?> = _param

    private val _schedule = MutableStateFlow<List<ScheduleDTO>?>(null)
    val schedules: StateFlow<List<ScheduleDTO>?> = _schedule

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isLoadingCategory = MutableStateFlow(false)
    val isLoadingCategory: StateFlow<Boolean> = _isLoadingCategory

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun onParamChanged(newParam: String) {
        _param.value = newParam
    }

    fun getSchedule(onSuccess: (List<ScheduleDTO>) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = scheduleRepository.getSchedule(_param.value.toString())
                val schedules = response.toSchedules()
                _schedule.value = schedules
                onSuccess(schedules)
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteSchedule(scheduleId: String,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val response = scheduleRepository.deleteSchedule(scheduleId)

                getSchedule(
                    onSuccess = { onSuccess() },
                    onError = { onError(it) }
                )
//                val schedules = response.toSchedules()
//                _schedule.value = schedules
////                onSuccess()
//                if (onSuccess != null) {
//                    getSchedule(schedules)
//                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                onError(e.message ?: "Erro desconhecido")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
