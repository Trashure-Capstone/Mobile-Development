package com.example.trashure.model

import android.view.Menu
import com.example.trashure.R


data class MenuSections(
    val title: String,
    val menuItems: List<MenuItems>,
)


data class MenuItems (
    val title : String,
    val price : String,
    val imageID : Int,
)


val menuSections = listOf(
    MenuSections(
    "Best Seller",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
    MenuSections(
        "Tas",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
    MenuSections(
        "Sepatu",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
    MenuSections(
        "Topi",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
    MenuSections(
        "Sandal",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
    MenuSections(
        "Mainan",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
    MenuSections(
        "Kerajinan",
        listOf(
            MenuItems("Cappuccino", "20.000", R.drawable.bottle),
            MenuItems("Espresso", "18.000", R.drawable.bottle),
            MenuItems("Latte", "22.000", R.drawable.bottle),
            MenuItems("Mocha", "24.000", R.drawable.bottle)
        )
    ),
)