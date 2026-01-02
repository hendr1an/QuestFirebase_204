package com.example.myfirebase.view.route

import com.example.myfirebase.R

object DestinasiEdit : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa
    const val itemIdArg = "itemId" // Variabel ini yang dicari EditViewModel
    val routeWithArgs = "$route/{$itemIdArg}"
}