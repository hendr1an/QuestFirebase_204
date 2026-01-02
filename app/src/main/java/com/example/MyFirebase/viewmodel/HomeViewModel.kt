package com.example.myfirebase.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirebase.modeldata.Siswa
import com.example.myfirebase.repositori.RepositorySiswa
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

// Status UI untuk memantau kondisi data (Loading, Sukses, atau Error)
sealed interface StatusUiSiswa {
    data class Success(val siswa: List<Siswa>) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}

class HomeViewModel(private val repositorySiswa: RepositorySiswa) : ViewModel() {

    // State yang akan dipantau oleh HomeScreen
    var statusUiSiswa: StatusUiSiswa by mutableStateOf(StatusUiSiswa.Loading)
        private set

    init {
        loadSiswa()
    }

    fun loadSiswa() {
        viewModelScope.launch {
            // Mengambil data real-time dari repository
            repositorySiswa.getAllSiswa()
                .onStart {
                    statusUiSiswa = StatusUiSiswa.Loading
                }
                .catch {
                    statusUiSiswa = StatusUiSiswa.Error
                }
                .collect { daftarSiswa ->
                    statusUiSiswa = StatusUiSiswa.Success(daftarSiswa)
                }
        }
    }
}