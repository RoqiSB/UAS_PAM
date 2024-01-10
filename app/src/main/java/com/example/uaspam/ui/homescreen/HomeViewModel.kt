package com.example.uaspam.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.ui.HomeUIState
import com.example.uaspam.data.AppRepository
import com.example.uaspam.model.Aplikasi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class AppUIState {
    data class Success(val aplikasi: Flow<List<Aplikasi>>) : AppUIState()
    object Error : AppUIState()
    object Loading : AppUIState()
}

class HomeViewModel(private val appRepository: AppRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = appRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listApp = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )

}