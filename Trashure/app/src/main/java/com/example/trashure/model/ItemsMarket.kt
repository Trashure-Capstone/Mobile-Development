package com.example.trashure.model

import com.example.trashure.R

data class ItemsMarket (
    val image : Int,
    val title : String,
    val price : String,
)


val dummyItemsMarket = listOf(
    ItemsMarket(R.drawable.lampu, "Lampu Ajaib", "30.000"),
    ItemsMarket(R.drawable.pot_bunga, "Pot Bunga", "18.000"),
    ItemsMarket(R.drawable.tas_kuning, "Tas Kuning", "25.000"),
    ItemsMarket(R.drawable.sandal_merah, "Sandal Merah", "24.000"),
)