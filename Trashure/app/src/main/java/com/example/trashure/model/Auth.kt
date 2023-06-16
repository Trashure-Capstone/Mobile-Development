package com.example.trashure.model

import com.google.gson.annotations.SerializedName

data class Auth (
    @field:SerializedName("isLogin")
    val isLogin: Boolean,
    
    @field:SerializedName("token")
    val token:String
    )
    
