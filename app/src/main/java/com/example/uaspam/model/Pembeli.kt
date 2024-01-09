package com.example.uaspam.model

data class Pembeli(

    val id: String,
    val nama: String,
    val email: String,
    val telpon: String

){
    constructor(): this("","","","")
}