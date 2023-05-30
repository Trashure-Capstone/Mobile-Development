package com.example.trashure.model

import com.example.trashure.R

data class News(
    val image : Int,
    val description: String,
)

val dummyNews = listOf(
    News(R.drawable.sampah , "Dari Jawa Barat Untuk Indonesia"),
    News(R.drawable.sampah , "Dari Jawa Barat Untuk Indonesia"),
    News(R.drawable.sampah , "Dari Jawa Barat Untuk Indonesia"),
)