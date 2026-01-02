package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.myfirebase.modeldata.DetailSiswa
import com.example.myfirebase.modeldata.UIStateSiswa
import com.example.myfirebase.modeldata.toDataSiswa
import com.example.myfirebase.repositori.RepositorySiswa

class EntryViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    // State untuk menampung input dari form Tambah Siswa
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    /* Fungsi untuk memvalidasi apakah semua kolom sudah diisi */
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return with(uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    // Memperbarui state setiap kali user mengetik di form
    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailSiswa,
            isEntryValid = validasiInput(detailSiswa)
        )
    }

    /* Fungsi untuk menyimpan data ke Firebase */
    suspend fun addSiswa() {
        if (validasiInput()) {
            // Menggunakan insertSiswa agar cocok dengan RepositorySiswa.kt
            repositorySiswa.insertSiswa(uiStateSiswa.detailSiswa.toDataSiswa())
        }
    }
}