package com.example.uaspam

import android.app.Application
import com.example.uaspam.data.AplikasiContainer
import com.example.uaspam.data.AppContainer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class Aplikasi : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AplikasiContainer()
    }

}