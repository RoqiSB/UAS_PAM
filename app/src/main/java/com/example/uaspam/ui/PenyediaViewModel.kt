package com.example.uaspam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.Aplikasi
import com.example.uaspam.ui.add.AddViewModel
import com.example.uaspam.ui.detail.DetailViewModel
import com.example.uaspam.ui.edit.EditViewModel
import com.example.uaspam.ui.homescreen.HomeViewModel

fun CreationExtras.aplikasiapp(): Aplikasi =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Aplikasi)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(aplikasiapp().container.appRepository)
        }
        initializer {
            HomeViewModel(aplikasiapp().container.appRepository)
        }
        initializer {
            DetailViewModel(
                createSavedStateHandle(),
                aplikasiapp().container.appRepository
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiapp().container.appRepository
            )
        }
    }
}