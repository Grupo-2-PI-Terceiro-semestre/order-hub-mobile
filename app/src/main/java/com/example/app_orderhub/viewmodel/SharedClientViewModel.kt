package com.example.app_orderhub.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app_orderhub.domain.model.Client
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedClientViewModel : ViewModel() {
    private val _client = MutableStateFlow<Client?>(Client())
    val client: StateFlow<Client?> = _client

    fun setClient(client: Client) {
        _client.value = client
    }
}
