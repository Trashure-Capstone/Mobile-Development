package com.example.trashure.ui.screen.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.data.remote.response.LoginResponse
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.screen.login.LoginUIEvent
import com.example.trashure.ui.screen.login.LoginUIState
import com.example.trashure.utils.Validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    private val _loginState: MutableStateFlow<UiState<LoginResponse>> =
        MutableStateFlow(UiState.Loading)
    val loginState: StateFlow<UiState<LoginResponse>>
        get() = _loginState
    
    private val _isLogin: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState.Loading)
    val isLogin: StateFlow<UiState<Boolean>>
        get() = _isLogin
    
    private val loginUIState = mutableStateOf(LoginUIState())
    
    private val isAllValidationsPassed = mutableStateOf(false)
    
    private val _isLoginInProgress = MutableStateFlow(false)
    val isLoginInProgress: StateFlow<Boolean> = _isLoginInProgress
    
    fun checkIsLogin() {
        _isLogin.value = UiState.Loading
        
        viewModelScope.launch{
            try {
                val isLogin = repository.isLogin()
                _isLogin.value = UiState.Success(isLogin)
            } catch (e:Exception){
                _isLogin.value = UiState.Error(e.message?: "Unknown Error")
            }
        }
    }
    
    private fun login(email:String, password:String){
        TODO()
    }
    
    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
            
            is LoginUIEvent.LoginButtonClicked -> {
                login(loginUIState.value.email, loginUIState.value.password)
            }
        }
        validateLoginUIDataWithRules()
    }
    
    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )
        
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        
        isAllValidationsPassed.value = emailResult.status && passwordResult.status
        
    }
    
}