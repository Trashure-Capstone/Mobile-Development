package com.example.trashure.model

data class TrashType(
    val id: String,
    val name: String,
    val pricePerKg: Int,
)

val TrashTypeList = listOf(
    TrashType("1", "PET", 100),
    TrashType("2","HDPE", 150),
    TrashType("3", "PVC", 150),
    TrashType("4", "LDPE", 100),
    TrashType("5", "PP", 200),
    TrashType("6", "PS", 250),
    TrashType("7", "Other_Plastic", 50)
)