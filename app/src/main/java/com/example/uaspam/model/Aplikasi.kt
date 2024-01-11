package com.example.uaspam.model

data class Aplikasi(

    val id: String,
    val nama: String,
    val ketr: String,
    val harga: String,
    val listapps: String

){
    constructor(): this("","","", "", "")
}