package com.example.trashure.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.trashure.R
import com.example.trashure.data.local.datastore.TrashurePreferencesDatastore
import com.example.trashure.data.remote.response.LoginResponse
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
    
    var preferencesDatastore = TrashurePreferencesDatastore(context)
    
    fun setAuth(loginResponse: LoginResponse){
        CoroutineScope(Dispatchers.IO).launch{
            preferencesDatastore.setAuth(
                Auth(
                    !loginResponse.error!!,
                    loginResponse.loginResult!!.token,
                    loginResponse.loginResult.name
                )
            )
        }
    }

    fun login(email:String, password:String): Flow<LoginResponse> {
        return flow{
            CoroutineScope(Dispatchers.IO).launch {
                apiService.login(email, password)
            }
        }
    }
    
    fun logout(){
        CoroutineScope(Dispatchers.IO).launch{
            preferencesDatastore.setAuth(Auth(false,"",""))
        }
    }
    
    suspend fun register(name:String, email:String, password:String): RegisterResponse {
        return apiService.register(name, email, password)
    }
    
    fun isLogin():Boolean{
        var isLogin: Boolean = false
        CoroutineScope(Dispatchers.IO).launch{
            isLogin = preferencesDatastore.getAuth().first().isLogin
        }
        return isLogin
    }
    
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