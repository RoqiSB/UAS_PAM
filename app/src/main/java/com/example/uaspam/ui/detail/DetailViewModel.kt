package com.example.uaspam.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.AppRepository
import com.example.uaspam.ui.DetailUIState
import com.example.uaspam.ui.toDetailApp
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: AppRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val aplikasiId: String = checkNotNull(savedStateHandle[DetailDestination.aplikasiId])

    val uiState: StateFlow<DetailUIState> =
        repository.getAplikasiById(aplikasiId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailApp())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteAplikasi() {
        repository.delete(aplikasiId)

    }


}