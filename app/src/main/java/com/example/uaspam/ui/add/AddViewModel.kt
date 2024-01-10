package com.example.uaspam.ui.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uaspam.data.AppRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddUIState
import com.example.uaspam.ui.toApp

class AddViewModel(private val appRepository: AppRepository) : ViewModel() {

    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun addAplikasi() {
        appRepository.save(addUIState.addEvent.toApp())
    }
}