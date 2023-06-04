package com.example.trashure.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.trashure.R
import com.example.trashure.data.local.datastore.TrashurePreferencesDatastore
import com.example.trashure.data.remote.response.LoginResponse
import com.example.trashure.data.remote.response.LoginResult
import com.example.trashure.data.remote.response.RegisterResponse
import com.example.trashure.data.remote.service.ApiService
import com.example.trashure.model.Auth
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Response

class TrashureRepository(private val context: Context, private val apiService: ApiService) {
    
    private val preferencesDatastore = TrashurePreferencesDatastore(context)
    
    private fun setAuth(auth:Auth){
        CoroutineScope(Dispatchers.IO).launch{
            preferencesDatastore.setAuth(
                Auth(
                    auth.isLogin,
                    auth.token,
                    auth.name
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
                    setAuth(Auth(true,response.loginResult.token,response.loginResult.name))
                }
            }
            catch (e:Exception){
                emit(UiState.Error(e.message.toString()))
            }
            
        }
    
    fun logout(){
        setAuth(Auth(false,"",""))
    }
    
    suspend fun register(name:String, email:String, password:String): RegisterResponse {
        return apiService.register(name, email, password)
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