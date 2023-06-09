package com.example.trashure.data.remote.service

import com.example.trashure.data.remote.response.LoginResponse
import com.example.trashure.data.remote.response.RegisterResponse
import com.example.trashure.data.remote.response.ScanResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): RegisterResponse
    
    @Multipart
    @POST("stories")
    suspend fun scan(
        @Part file: MultipartBody.Part
    ): ScanResponse
}