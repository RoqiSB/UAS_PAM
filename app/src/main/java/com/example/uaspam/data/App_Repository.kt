package com.example.uaspam.data

import android.content.ContentValues
import android.util.Log
import com.example.uaspam.model.Aplikasi
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

interface AppRepository {
    fun getAll(): Flow<List<Aplikasi>>
    suspend fun save(aplikasi: Aplikasi): String
    suspend fun update(aplikasi: Aplikasi)
    suspend fun delete(aplikasiId: String)
    fun getAplikasiById(aplikasiId: String): Flow<Aplikasi>
}

class AppRepositoryImpl(private val firestore: FirebaseFirestore) : AppRepository {
    override fun getall(): Flow<List<Aplikasi>> = flow {
        val snapshot = firestore.collection("Aplikasi")
            .orderBy("nama", Query.Direction.ASCENDING)
            .get()
            .await()
        val aplikasi = snapshot.toObjects(Aplikasi::class.java)
        emit(aplikasi)
    }.flowOn(Dispatchers.IO)

    override suspend fun save(aplikasi: Aplikasi): String {
        return try {
            val documentReference = firestore.collection("Aplikasi").add(aplikasi).await()
            firestore.collection("Aplikasi").document(documentReference.id)
                .set(aplikasi.copy(id = documentReference.id))
            "Berhasil + ${documentReference.id}"
        } catch (e: Exception) {
            Log.w(ContentValues.TAG, "Error adding document", e)
            "Gagal $e"
        }
    }
    override suspend fun update(aplikasi: Aplikasi) {
        firestore.collection("Aplikasi").document(aplikasi.id).set(aplikasi).await()
    }

    override suspend fun delete(aplikasiId: String) {
        firestore.collection("Aplikasi").document(aplikasiId).delete().await()
    }

    override fun getKontakById(aplikasiId: String): Flow<Aplikasi> {
        return flow {
            val snapshot = firestore.collection("Aplikasi").document(aplikasiId).get().await()
            val aplikasi = snapshot.toObject(Aplikasi::class.java)
            emit(aplikasi!!)
        }.flowOn(Dispatchers.IO)
    }
}