package com.example.uaspam.model

data class Aplikasi(

    val id: String,
    val nama: String,
    val harga: String

){
    constructor(): this("","","")
}