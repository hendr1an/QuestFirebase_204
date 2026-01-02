package com.example.myfirebase.repositori

import com.google.firebase.firestore.FirebaseFirestore

interface ContainerApp {
    val repositorySiswa: RepositorySiswa
}

class DefaultContainerApp : ContainerApp {
    override val repositorySiswa: RepositorySiswa by lazy {
        NetworkRepositorySiswa(FirebaseFirestore.getInstance())
    }
}