package com.example.trashure.ui.screen.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.data.remote.response.LoginResult
import com.example.trashure.ui.common.UiState
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
    val loginUIState = mutableStateOf(LoginUIState())
    
    //handling state for validation
    val isAllValidationsPassed = mutableStateOf(false)
    
    fun checkIsLogin() {
        viewModelScope.launch{
            try {
                _isLogin.value = UiState.Loading
                val isLogin = repository.isLogin()
                _isLogin.value = UiState.Success(isLogin)
            } catch(e:Exception) {
                _isLogin.value = UiState.Error(e.message.toString())
            }
        }
    }
    
    private fun login(){
        Log.d("yyy","login")
        
        val email = loginUIState.value.email
        val password = loginUIState.value.password
        
        viewModelScope.launch {
            _loginState.value = UiState.Loading
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
                    email = event.email,
                    emailError = !Validator.validateEmail(event.email).status
                )
            }
            
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password,
                    passwordError = !Validator.validatePassword(event.password).status
                )
            }
            
            is LoginUIEvent.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }
    
    private fun validateLoginUIDataWithRules() {
    
        val emailValid = !loginUIState.value.emailError && loginUIState.value.email.isNotEmpty()
        val passwordValid = !loginUIState.value.passwordError && loginUIState.value.password.isNotEmpty()
        
        isAllValidationsPassed.value = emailValid && passwordValid
        
    }
    
}