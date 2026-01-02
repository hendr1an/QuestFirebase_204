package com.example.myfirebase.repositori

import com.example.myfirebase.modeldata.Siswa
import kotlinx.coroutines.flow.Flow

interface RepositorySiswa {
    // Fungsi untuk mengambil semua data siswa
    fun getAllSiswa(): Flow<List<Siswa>>

    // Fungsi ini yang dicari EditViewModel (HARUS ADA)
    fun getSiswaById(idSiswa: String): Flow<Siswa?>

    // Fungsi untuk menambah data
    suspend fun insertSiswa(siswa: Siswa)

    // Fungsi ini yang dicari EditViewModel (HARUS ADA)
    suspend fun updateSiswa(id: String, siswa: Siswa)

    // Fungsi untuk menghapus data
    suspend fun deleteSiswa(id: String)
}