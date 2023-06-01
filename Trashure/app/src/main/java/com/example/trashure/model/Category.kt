package com.example.trashure.model

import com.example.trashure.R

data class Category(
    val icon: Int,
    val title: String,
    val input: Int,
)

val dummyCategory = listOf(
    Category(R.drawable.money, "Saldo Anda", 30000),
    Category(R.drawable.coin, "Poin Anda", 300)
)