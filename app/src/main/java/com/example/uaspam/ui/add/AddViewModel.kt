package com.example.uaspam.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uaspam.data.AppRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddUIAppState
import com.example.uaspam.ui.toApp

class AddViewModel(private val appRepository: AppRepository) : ViewModel() {

    var addUIState by mutableStateOf(AddUIAppState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIAppState(addEvent = addEvent)
    }

    suspend fun addAplikasi() {
        appRepository.save(addUIState.addEvent.toApp())
    }
}