package com.example.trashure.model

data class OrderProgress (
    val id : String,
    val status : Boolean,
    val description: String,
    val time : String,
    val date : String,
)

val dummyOrderProgress = listOf(
    OrderProgress( "ID121212131", true,  "Pesanan akan di ambil oleh kurir, mohon menunggu", "17.40", "26 May 2023"),
    OrderProgress( "ID121212131", true, "Pesanan akan di ambil oleh kurir, mohon menunggu", "17.40", "26 May 2023"),
    OrderProgress( "ID121212131", true,  "Pesanan akan di ambil oleh kurir, mohon menunggu", "17.40", "26 May 2023")
)