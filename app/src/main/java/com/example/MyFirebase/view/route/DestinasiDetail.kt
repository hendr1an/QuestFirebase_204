package com.example.myfirebase.view.route

import com.example.myfirebase.R

object DestinasiDetail : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = R.string.detail_siswa

    // Variabel kunci yang menyebabkan error di ViewModel
    const val itemIdArg = "itemId"

    // Rute lengkap dengan argumen untuk navigasi
    val routeWithArgs = "$route/{$itemIdArg}"
}