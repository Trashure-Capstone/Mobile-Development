package com.example.trashure.model

data class OrderProgress (
    val id : Int,
    val status : Boolean,
    val title: String,
    val description: String,
)

val dummyOrderProgress = listOf(
    OrderProgress( 1, true, "Plastik", "Pesanan akan di ambil oleh kurir, mohon menunggu"),
    OrderProgress( 1, true, "Plastik", "Pesanan akan di ambil oleh kurir, mohon menunggu"),
    OrderProgress( 1, true, "Plastik", "Pesanan akan di ambil oleh kurir, mohon menunggu")
)