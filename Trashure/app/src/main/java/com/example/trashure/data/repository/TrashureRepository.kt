package com.example.trashure.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.trashure.data.local.datastore.TrashurePreferencesDatastore
import com.example.trashure.data.remote.response.LoginResult
import com.example.trashure.data.remote.response.RegisterResponse
import com.example.trashure.data.remote.service.ApiConfig
import com.example.trashure.data.remote.service.ApiService
import com.example.trashure.model.Auth
import com.example.trashure.model.News
import com.example.trashure.model.dummyNews
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class TrashureRepository(context: Context, private val apiService: ApiService) {
    
    private val preferencesDatastore = TrashurePreferencesDatastore(context)

    private val newsList = mutableListOf<News>()

    init {
        if (newsList.isEmpty()) {
            dummyNews.forEach {
                newsList.add(it)
            }
        }
    }

    fun getNewsList(): Flow<List<News>> {
        return flowOf(newsList)
    }

    fun getNewsById(id: Long): News {
        return newsList.first {
            it.id == id
        }
    }
    private fun setAuth(auth:Auth){
        CoroutineScope(Dispatchers.IO).launch{
            preferencesDatastore.setAuth(
                Auth(
                    auth.isLogin,
                    auth.token
                )
            )
        }
    }

    fun login(email:String, password:String): Flow<UiState<LoginResult>> = flow {
            
            try {
                emit(UiState.Loading)
                val response = apiService.login(email, password)
                if (response.error){
                    emit(UiState.Error(response.message))
                }else{
                    emit(UiState.Success(response.loginResult))
                    setAuth(Auth(true,response.loginResult.token))
                }
            }
            catch (e:Exception){
                emit(UiState.Error(e.message.toString()))
            }
            
        }
    
    fun logout(){
        setAuth(Auth(false,""))
    }
    
    fun register(name:String, email:String, password:String): Flow<UiState<RegisterResponse>> = flow {
        
        try {
            emit(UiState.Loading)
            val response = apiService.register(name, email, password)
            if (response.error){
                emit(UiState.Error(response.message))
            }else{
                emit(UiState.Success(response))
            }
        }
        catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }
        
    }
    
    fun getUserInfo() = flow{
        try {
            emit(UiState.Loading)
            val response = apiService.getUserInfo()
            if(response.error){
                emit(UiState.Error(response.message))
            } else{
                emit(UiState.Success(response.data.user))
            }
        } catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }
    
    fun scan(file: MultipartBody.Part, context:Context) = flow {
        try {
            emit(UiState.Loading)
            val response = ApiConfig.getApiServiceML(context).scan(file)
            Log.d("ScanResponses",response.toString())
            if (response.error){
                emit(UiState.Error(response.message))
            }else{
                emit(UiState.Success(response.result))
            }
        }
        catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }
    
    fun sellTrash(
        latitude: RequestBody,
        longitude: RequestBody,
        date: RequestBody,
        time: RequestBody,
        weight: RequestBody,
        trashType: RequestBody,
        image: MultipartBody.Part
    ) = flow {
        try{
            emit(UiState.Loading)
            val response = apiService.sellTrash(latitude,longitude,date,time,weight,trashType,image)
            if (response.error){
                emit(UiState.Error(response.message))
            }else{
                emit(UiState.Success(response))
            }
        }catch (e:Exception){
            emit(UiState.Error(e.message.toString()))
        }
    }
    
    suspend fun isLogin() = preferencesDatastore.getAuth().first().isLogin
    
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: TrashureRepository? = null

        fun getInstance(
            context: Context,
            apiService: ApiService
        ): TrashureRepository =
            instance ?: synchronized(this) {
                TrashureRepository(context, apiService).apply {
                    instance = this
                }
            }
    }
}