package com.example.myfirebase.repositori

import com.example.myfirebase.modeldata.Siswa
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class NetworkRepositorySiswa(
    private val firestore: FirebaseFirestore
) : RepositorySiswa {

    // Pastikan nama koleksi "siswa" sama dengan di Firebase Console Anda
    private val siswaCollection = firestore.collection("siswa")

    // Mengambil semua data siswa secara realtime
    override fun getAllSiswa(): Flow<List<Siswa>> = callbackFlow {
        val subscription = siswaCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val items = snapshot.toObjects(Siswa::class.java)
                trySend(items)
            }
        }
        awaitClose { subscription.remove() }
    }

    // Mengambil satu data siswa berdasarkan ID
    override fun getSiswaById(id: String): Flow<Siswa?> = callbackFlow {
        val subscription = siswaCollection.document(id).addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val item = snapshot.toObject(Siswa::class.java)
                trySend(item)
            }
        }
        awaitClose { subscription.remove() }
    }

    // Menambah data siswa baru
    override suspend fun insertSiswa(siswa: Siswa) {
        try {
            siswaCollection.add(siswa).await()
        } catch (e: Exception) {
            throw e
        }
    }

    // Update data siswa (Membutuhkan ID dan objek Siswa)
    // Perbaikan untuk error pada image_ec19ab.jpg dan image_ec09ae.jpg
    override suspend fun updateSiswa(id: String, siswa: Siswa) {
        try {
            siswaCollection.document(id).set(siswa).await()
        } catch (e: Exception) {
            throw e
        }
    }

    // Hapus data siswa berdasarkan ID
    // Perbaikan untuk error pada image_ec1192.jpg dan image_ec09ae.jpg
    override suspend fun deleteSiswa(id: String) {
        try {
            siswaCollection.document(id).delete().await()
        } catch (e: Exception) {
            throw e
        }
    }
}