package com.example.trashure.model

import com.example.trashure.R

data class Menu(
    val icon : Int,
    val title: String,
    val description: String,
)

val dummyMenu = listOf(
    Menu(R.drawable.recycling, "Jual Sampah", "Bersihkan lingkunganmu sekarang"),
    Menu(R.drawable.sampah,"Toko Sampah","Dapatkan sampah untuk keperluan bisnis")
)
