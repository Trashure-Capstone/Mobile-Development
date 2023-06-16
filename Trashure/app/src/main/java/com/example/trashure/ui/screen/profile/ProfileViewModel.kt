package com.example.trashure.ui.screen.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.data.remote.response.LoginResult
import com.example.trashure.data.remote.response.User
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.screen.login.LoginUIEvent
import com.example.trashure.ui.screen.login.LoginUIState
import com.example.trashure.utils.Validator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    
    //handling state for login response
    private val _state: MutableStateFlow<UiState<User>> =
        MutableStateFlow(UiState.Empty)
    val state: StateFlow<UiState<User>> = _state
    
    fun getUserInfo(){
        viewModelScope.launch {
            _state.value = UiState.Loading
            repository.getUserInfo()
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect{user->
                    _state.value = user
                }
        }
    }
    fun logout(){
        repository.logout()
    }
    
    
}