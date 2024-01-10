package com.example.uaspam.ui

import com.example.uaspam.model.Aplikasi


data class AddUIAppState(
    val addEvent: AddEvent = AddEvent(),
)

data class AddEvent(
    val id: String = "",
    val nama: String = "",
    val ketr: String = "",
    val harga: String = "",
)

fun AddEvent.toApp() = Aplikasi(
    id = id,
    nama = nama,
    ketr = ketr,
    harga = harga
)

data class DetailUIState(
    val addEvent: AddEvent = AddEvent(),
)

fun Aplikasi.toDetailApp(): AddEvent =
    AddEvent(
        id = id,
        nama = nama,
        ketr = ketr,
        harga = harga
    )

fun Aplikasi.toUIStateApp(): AddUIAppState = AddUIAppState(
    addEvent = this.toDetailApp()
)

data class HomeUIState(
    val listApp: List<com.example.uaspam.model.Aplikasi> = listOf(),
    val dataLength: Int = 0
)