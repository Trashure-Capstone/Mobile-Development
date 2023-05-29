package com.example.trashure.model

import com.example.trashure.R

data class Category(
    val icon: Int,
    val title: String,
    val input: String,
)

val dummyCategory = listOf(
    R.drawable.money to "Saldo Anda" to "Rp. 30.000",
    R.drawable.coin to "Poin Anda" to "300 Coin"
)