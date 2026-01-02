package com.example.myfirebase.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myfirebase.view.route.DestinasiDetail
import com.example.myfirebase.viewmodel.DetailViewModel
import com.example.myfirebase.viewmodel.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateToEditItem: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    // Mengambil status UI dari ViewModel secara real-time
    val uiState = viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            // Memanggil SiswaTopAppBar yang sudah Anda buat sebelumnya
            SiswaTopAppBar(
                title = stringResource(DestinasiDetail.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            // Tombol Edit yang mengirimkan ID siswa ke navigasi
            FloatingActionButton(
                onClick = { navigateToEditItem(uiState.value.id) }
            ) {
                Text(text = "Edit")
            }
        }
    ) { innerPadding ->
        // Menampilkan data siswa yang diambil dari Firebase
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Nama: ${uiState.value.nama}")
            Text(text = "Alamat: ${uiState.value.alamat}")
            Text(text = "Telepon: ${uiState.value.telpon}")
        }
    }
}