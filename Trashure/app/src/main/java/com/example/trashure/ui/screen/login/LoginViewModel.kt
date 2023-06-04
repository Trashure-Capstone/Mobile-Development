package com.example.trashure.ui.screen.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.data.remote.response.LoginResponse
import com.example.trashure.data.remote.response.LoginResult
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.screen.login.LoginUIEvent
import com.example.trashure.ui.screen.login.LoginUIState
import com.example.trashure.utils.Validator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    
    //handling state for login response
    private val _loginState: MutableStateFlow<UiState<LoginResult>> =
        MutableStateFlow(UiState.Empty)
    val loginState: StateFlow<UiState<LoginResult>> = _loginState
    
    //handling state when checking is user signed in
    private val _isLogin: MutableStateFlow< UiState<Boolean>> =
        MutableStateFlow(UiState.Empty)
    val isLogin: StateFlow<UiState<Boolean>> = _isLogin
    
    //handling state of text field
    private val loginUIState = mutableStateOf(LoginUIState())
    
    //handling state for validation
    private val isAllValidationsPassed = mutableStateOf(false)
    
    fun checkIsLogin() {
        _isLogin.value = UiState.Loading
        viewModelScope.launch{
            try {
                val isLogin = repository.isLogin()
                _isLogin.value = UiState.Success(isLogin)
            } catch(e:Exception) {
                _isLogin.value = UiState.Error(e.message.toString())
            }
        }
    }
    
    fun logout(){
        repository.logout()
    }
    
    private fun login(){
        
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        Log.d("loginTest",_loginState.value.toString())
        _loginState.value = UiState.Loading
        Log.d("loginTest",_loginState.value.toString())
        viewModelScope.launch {
            repository.login(email, password)
                .catch {
                    _loginState.value = UiState.Error(it.message.toString())
                    
                }
                .collect{
                    loginResult ->
                    _loginState.value = loginResult
                }
        }
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
                Log.d("eventTest", loginUIState.value.password)
            }
            
            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }
    
    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        Log.d("validateTest", loginUIState.value.email)
        
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )
        Log.d("validateTest", loginUIState.value.password)
        
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        
        isAllValidationsPassed.value = emailResult.status && passwordResult.status
        
    }
    
}