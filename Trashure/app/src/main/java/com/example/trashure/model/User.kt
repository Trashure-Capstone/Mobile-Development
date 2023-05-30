package com.example.trashure.model

import android.media.Image
import com.example.trashure.R

data class User (
    val username : String,
    val email : String,
    val password : String,
    val image: Int,
    val phoneNumber: String,
)

val dummyUser = listOf(
    User("Hilalhmdy", "hilal@gmail.com", "***", R.drawable.avatarss, "082172227272" )
)