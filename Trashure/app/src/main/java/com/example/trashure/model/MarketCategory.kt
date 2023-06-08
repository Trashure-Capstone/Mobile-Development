package com.example.trashure.model

import com.example.trashure.R

data class MarketCategory (
    val icon : Int,
    val title : String
)

val dummyMarketCategory = listOf(
    MarketCategory(R.drawable.shopping_basket, "Trashure Market"),
    MarketCategory(R.drawable.store, "UMKM Market")

)