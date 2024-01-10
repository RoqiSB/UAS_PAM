package com.example.uaspam.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.AppRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddUIAppState
import com.example.uaspam.ui.toApp
import com.example.uaspam.ui.toUIStateApp
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: AppRepository
) : ViewModel() {

    var appUIState by mutableStateOf(AddUIAppState())
        private set

    private val appId: String = checkNotNull(savedStateHandle[EditDestination.appid])

    init {
        viewModelScope.launch {
            appUIState =
                repository.getAplikasiById(appId)
                    .filterNotNull()
                    .first()
                    .toUIStateApp()
        }
    }

    fun updateUIState(addEvent: AddEvent) {
        appUIState = appUIState.copy(addEvent = addEvent)
    }

    suspend fun updateAplikasi() {
        repository.update(appUIState.addEvent.toApp())

    }
}