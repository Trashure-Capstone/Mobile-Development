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
            MenuItems("Lampu Ajaib", "30.000", R.drawable.lampu),
            MenuItems("Pot Bunga", "18.000", R.drawable.pot_bunga),
            MenuItems("Tas Kuning", "25.000", R.drawable.tas_kuning),
            MenuItems("Sandal Mewah", "24.000", R.drawable.sandal_merah)
        )
    ),
    MenuSections(
        "Tas",
        listOf(
            MenuItems("Tas Merah", "30.000", R.drawable.tas_merah),
            MenuItems("Tas Minuman", "20.000", R.drawable.tas_minuman),
            MenuItems("Tas Ungu", "22.000", R.drawable.tas_ungu)
        )
    ),
    MenuSections(
        "Kerajinan",
        listOf(
            MenuItems("Bottle Flower", "20.000", R.drawable.kerajinan_bunga),
            MenuItems("Wadah Plastik", "18.000", R.drawable.kerajinan_wadah)
        )
    ),
    MenuSections(
        "Keranjang",
        listOf(
            MenuItems("Keranjang Botol", "20.000", R.drawable.keranjang_botol),
            MenuItems("Kerajnjang Minuman", "18.000", R.drawable.keranjang_minuman),
            MenuItems("Keranjang Hijau", "22.000", R.drawable.keranjang),
        )
    ),
    MenuSections(
        "Sandal",
        listOf(
            MenuItems("Sandal ABC", "20.000", R.drawable.sandal_abc),
            MenuItems("Sandal Bangau", "18.000", R.drawable.sandal_bangau),
            MenuItems("Sandal Indomie", "22.000", R.drawable.sandal_indomie)
        )
    ),
    MenuSections(
        "Topi",
        listOf(
            MenuItems("Topi Daia", "20.000", R.drawable.topi_daia),
            MenuItems("Topi Kemasan", "18.000", R.drawable.topi_kemasan),
            MenuItems("Topi Sunlight", "22.000", R.drawable.topi_sunlight)
        )
    ),
    MenuSections(
        "Tikar",
        listOf(
            MenuItems("Tikar Merah", "20.000", R.drawable.tikar_merah),
            MenuItems("Tikar Kuning", "18.000", R.drawable.tikar)
        )
    ),
)