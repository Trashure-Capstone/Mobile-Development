package com.example.trashure.data.remote.response

import com.google.gson.annotations.SerializedName

data class ScanResponse (
    @field:SerializedName("jenis_sampah")
    val jenis_sampah: Boolean,
    
    @field:SerializedName("tipe_sampah")
    val tipe_sampah: String,
    
    @field:SerializedName("nama_lain")
    val nama_lain: String,
    
    @field:SerializedName("manfaat")
    val manfaat: String,

    @field:SerializedName("kekurangan")
    val kekurangan: String,
    )