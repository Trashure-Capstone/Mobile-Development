package com.example.trashure.ui.screen.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.data.remote.response.RegisterResponse
import com.example.trashure.ui.common.UiState
import com.example.trashure.utils.Validator
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    
    //handling state for register response
    private val _registerState: MutableStateFlow<UiState<RegisterResponse>> =
        MutableStateFlow(UiState.Empty)
    val registerState: StateFlow<UiState<RegisterResponse>> = _registerState
    
    //handling state of text field
    val registerUIState = mutableStateOf(RegisterUIState())
    
    //handling state for validation
    val isAllValidationsPassed = mutableStateOf(false)
    
    private fun register(){
        val name = registerUIState.value.name
        val email = registerUIState.value.email
        val password = registerUIState.value.password
        _registerState.value = UiState.Loading
        viewModelScope.launch {
            repository.register(name, email, password)
                .catch {
                    _registerState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    registerResponse ->
                    _registerState.value = registerResponse
                }
        }
    }
    
    fun onEvent(event: RegisterUIEvent) {
        when (event) {
            is RegisterUIEvent.NameChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    name = event.name
                )
            }
            
            is RegisterUIEvent.EmailChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    email = event.email
                )
            }
            
            is RegisterUIEvent.PasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    password = event.password
                )
            }
    
            is RegisterUIEvent.ConfirmPasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
            }
            
            is RegisterUIEvent.RegisterButtonClicked -> {
                register()
            }
        }
        validateRegisterUIDataWithRules()
    }
    
    private fun validateRegisterUIDataWithRules() {
        val nameResult = Validator.validateName(
            name = registerUIState.value.name
        )
        
        val emailResult = Validator.validateEmail(
            email = registerUIState.value.email
        )
        
        val passwordResult = Validator.validatePassword(
            password = registerUIState.value.password
        )
    
        val confirmPasswordResult = Validator.validateConfirmPassword(
            password = registerUIState.value.password,
            confirmPassword = registerUIState.value.confirmPassword
        )
        
        registerUIState.value = registerUIState.value.copy(
            nameError = nameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            confirmPasswordError =  confirmPasswordResult.status
        )
        
        isAllValidationsPassed.value =
            nameResult.status && emailResult.status
                    && passwordResult.status && confirmPasswordResult.status
        
    }
    
}