package com.example.trashure.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.trashure.model.Auth
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("trashure_datastore")

class TrashurePreferencesDatastore(context: Context) {
    
    private var pref = context.dataStore
    
    suspend fun setAuth(auth : Auth){
        pref.edit {
            it[isLogin] = auth.isLogin
            it[token]   = auth.token
        }
    }
    
    suspend fun getAuth(): Auth {
        return pref.data.map{
            Auth(
                isLogin = it[isLogin]?:false,
                token   = it[token]?:""
            )
        }.first()
    }
    
    companion object{
        var isLogin = booleanPreferencesKey("isLogin")
        var name = stringPreferencesKey("name")
        var token = stringPreferencesKey("token")
    }
}