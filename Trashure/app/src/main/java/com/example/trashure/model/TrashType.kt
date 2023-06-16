package com.example.trashure.model

data class TrashType(
    val id: String,
    val name: String,
    val pricePerKg: Int,
    val logo: String
)

val TrashTypeList = listOf(
    TrashType("1", "PET", 1000, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_1_PETE.svg"),
    TrashType("2","HDPE", 2000, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_2_HDPE.svg"),
    TrashType("3", "PVC", 2000, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_3_V.svg"),
    TrashType("4", "LDPE", 2500, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_4_LDPE.svg"),
    TrashType("5", "PP", 1500, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_5_PP.svg"),
    TrashType("6", "PS", 1000, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_6_PS.svg"),
    TrashType("7", "OTHER", 3000, "https://storage.googleapis.com/trashurebucket/dic_ml/Symbol_Resin_Code_7_OTHER.svg")
)