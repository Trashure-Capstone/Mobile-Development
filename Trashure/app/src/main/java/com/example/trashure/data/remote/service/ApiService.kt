package com.example.trashure.data.remote.service

import com.example.trashure.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse
    
    @POST("auth/check-token")
    suspend fun getUserInfo(): UserResponse
    
    @Multipart
    @POST("scan")
    suspend fun scan(
        @Part file: MultipartBody.Part
    ): ScanResponse
    
    @Multipart
    @POST("jual-sampah")
    suspend fun sellTrash(
        @Part("latitude") latitude:RequestBody,
        @Part("longitude") longitude:RequestBody,
        @Part("tanggal_pengambilan") date:RequestBody,
        @Part("waktu") time:RequestBody,
        @Part("berat_sampah") weight:RequestBody,
        @Part("id_sampah") trashType:RequestBody,
        @Part file: MultipartBody.Part
    ): SellTrashResponse
}