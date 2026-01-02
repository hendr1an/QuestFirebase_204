package com.example.myfirebase.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.repositori.RepositorySiswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositorySiswa: RepositorySiswa
) : ViewModel() {

    // Mengambil ID siswa dari navigasi
    private val siswaId: String = checkNotNull(savedStateHandle["idSiswa"])

    // Menampilkan data siswa secara realtime dari Firebase
    val uiState: StateFlow<Siswa> = repositorySiswa.getSiswaById(siswaId)
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Siswa() // Pastikan model Siswa punya nilai default
        )

    /* Fungsi untuk menghapus data siswa berdasarkan ID */
    fun deleteSiswa() {
        viewModelScope.launch {
            try {
                // Perbaikan: Mengirimkan ID (String), bukan objek Siswa
                // Ini memperbaiki error pada image_ec1192.jpg
                repositorySiswa.deleteSiswa(siswaId)
            } catch (e: Exception) {
                // Log error jika diperlukan
            }
        }
    }
}