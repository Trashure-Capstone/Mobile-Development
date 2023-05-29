package com.example.trashure.model

import com.example.trashure.R

data class News(
    val image : Int,
    val description: String,
)

val dummyNews = listOf(
    R.drawable.sampah to "Dari Jawa Barat Untuk Indonesia",
    R.drawable.sampah to "Dari Jawa Barat Untuk Indonesia",
    R.drawable.sampah to "Dari Jawa Barat Untuk Indonesia"
)