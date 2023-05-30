package com.example.trashure.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.trashure.R
import com.example.trashure.ui.theme.Blue_1
import com.example.trashure.ui.theme.Green_1

data class Menu(
    val icon : Int,
    val title: String,
    val description: String,
    val color: Color
)

val dummyMenu = listOf(
    Menu(R.drawable.recycling, "Jual Sampah", "Bersihkan lingkunganmu sekarang", Green_1)
)

val dummyMenuToko = listOf(
    Menu(R.drawable.shopping, "Toko Sampah", "Dapatkan sampah untuk keperluan bisnis", Blue_1)
)