package com.example.trashure.model

import java.util.TimeZone

data class OrderFinish (
    val id: Int,
    val title : String,
    val status: Boolean,
    val time: String,
    val date: String,
)

val dummyOrderFinish = listOf(
    OrderFinish(1, "Plastik", true, "17.40" , "26 May 2023"),
    OrderFinish(1, "Plastik", true, "17.40" , "26 May 2023"),
    OrderFinish(1, "Plastik", true, "17.40" , "26 May 2023")
)