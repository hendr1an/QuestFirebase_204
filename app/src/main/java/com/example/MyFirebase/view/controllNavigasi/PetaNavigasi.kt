package com.example.myfirebase.view.controllNavigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myfirebase.view.HomeScreen
import com.example.myfirebase.view.EntrySiswaScreen
import com.example.myfirebase.view.route.DestinasiHome
import com.example.myfirebase.view.route.DestinasiEntry
import com.example.myfirebase.view.route.DestinasiDetail

@Composable
fun DataSiswaApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    HostNavigasi(navController = navController, modifier = modifier)
}

@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = modifier
    ) {
        // Halaman Utama (Daftar Siswa)
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntry.route)
                },
                onDetailClick = { idSiswa ->
                    navController.navigate("${DestinasiDetail.route}/$idSiswa")
                }
            )
        }

        // Halaman Tambah Siswa
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        // Halaman Detail Siswa (Menggunakan Argumen ID)
        composable(
            route = DestinasiDetail.routeWithArgs,
            arguments = listOf(
                navArgument(DestinasiDetail.itemIdArg) {
                    type = NavType.StringType
                }
            )
        ) {
            // Anda bisa memanggil DetailScreen di sini jika sudah dibuat
            // DetailScreen(navigateBack = { navController.popBackStack() })
        }
    }
}