package com.example.trashure.data.remote.response

import com.google.gson.annotations.SerializedName

data class SellTrashResponse (
    @field:SerializedName("error")
    val jenis_sampah: Boolean,
    
    @field:SerializedName("message")
    val tipe_sampah: String,
    
)
