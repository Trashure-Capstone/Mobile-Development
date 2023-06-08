package com.example.trashure.model

import com.example.trashure.R

data class ItemsMarket (
    val image : Int,
    val title : String,
    val price : String,
)


val dummyItemsMarket = listOf(
    ItemsMarket(R.drawable.bottle, "Plastic Shoes", "12.000"),
    ItemsMarket(R.drawable.bottle, "Plastic Shoes", "12.000"),
    ItemsMarket(R.drawable.bottle, "Plastic Shoes", "12.000"),
)