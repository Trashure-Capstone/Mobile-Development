package com.example.trashure.model

data class Message(
    val title : String,
    val description : String,
)

val dummyMessage = listOf(
    Message( "Kumpulkan Sampahmu!", "Dapatkan poin sebanyak-banyaknya untuk ditukar dengan merchandise yang ada di penawaran"),
    Message( "Kumpulkan Sampahmu!", "Dapatkan poin sebanyak-banyaknya untuk ditukar dengan merchandise yang ada di penawaran"),
    Message( "Kumpulkan Sampahmu!", "Dapatkan poin sebanyak-banyaknya untuk ditukar dengan merchandise yang ada di penawaran")
)