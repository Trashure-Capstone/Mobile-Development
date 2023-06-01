package com.example.trashure.data.remote.service

import android.content.Context
import com.example.trashure.data.local.datastore.TrashurePreferencesDatastore
import com.example.trashure.model.Auth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object{

        private fun getToken(context: Context):String{
            val auth : Auth
            runBlocking {
                auth = TrashurePreferencesDatastore(context).getAuth().first()
            }
            return auth.token
        }

        fun getApiService(context: Context): ApiService {
            val token = getToken(context)
            val tokenInterceptor = Interceptor{chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            
            val client = OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://story-api.dicoding.dev/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}