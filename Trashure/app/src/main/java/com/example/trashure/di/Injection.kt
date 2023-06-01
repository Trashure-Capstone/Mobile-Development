package com.example.trashure.di

import android.content.Context
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.data.remote.service.ApiConfig

object Injection {
    fun provideRepository(context: Context): TrashureRepository {
        val apiService = ApiConfig.getApiService(context)
        return TrashureRepository.getInstance(context,apiService)
    }
}