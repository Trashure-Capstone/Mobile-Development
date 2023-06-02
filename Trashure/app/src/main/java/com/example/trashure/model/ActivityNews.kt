package com.example.trashure.model

import com.example.trashure.R

data class ActivityNews (
    val id : Int,
    val image: Int,
)

val dummyActivity = listOf(
    ActivityNews( 1, R.drawable.banner, ),
    ActivityNews( 1, R.drawable.banner, ),
)