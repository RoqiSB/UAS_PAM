package com.example.uaspam.data

import com.example.uaspam.util.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository {
    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }

    override fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        TODO("Not yet implemented")
    }
}