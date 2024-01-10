package com.example.uaspam.data

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val appRepository: AppRepository
}

class AppContainer : AppContainer {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    override val appRepository : AppRepository by lazy {
        AppRepositoryImpl(firestore)
    }
}