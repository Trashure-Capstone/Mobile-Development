package com.example.trashure.model

import java.util.TimeZone

data class OrderFinish (
    val id: Int,
    val title : String,
    val status: Boolean,
    val time: String,
    val date: String,
)