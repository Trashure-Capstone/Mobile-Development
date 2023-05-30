package com.example.trashure.model

import com.example.trashure.R

data class ActivityNews (
    val image: Int,
    val title : String,
)

val dummyActivity = listOf(
    ActivityNews(R.drawable.banner, ""),
    ActivityNews(R.drawable.banner, ""),
)