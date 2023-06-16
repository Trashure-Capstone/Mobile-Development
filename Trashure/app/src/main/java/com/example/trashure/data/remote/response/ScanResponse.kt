package com.example.trashure.data.remote.response

import com.google.gson.annotations.SerializedName

data class ScanResponse (
    @field:SerializedName("error")
    val error: Boolean,
    
    @field:SerializedName("message")
    val message: String,
    
    @field:SerializedName("result")
    val result: ScanResult
    )

data class ScanResult(
    @field:SerializedName("jenis_sampah")
    val jenis_sampah: String,
    
    @field:SerializedName("kekurangan")
    val kekurangan: String,

    @field:SerializedName("logo")
    val logo:String,

    @field:SerializedName("manfaat")
    val manfaat: String,
    
    @field:SerializedName("nama_lain")
    val nama_lain: String,

    @field:SerializedName("tipe_sampah")
    val tipe_sampah: String
    )