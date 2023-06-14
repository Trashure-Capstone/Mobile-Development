package com.example.trashure.ui.screen.register

import android.util.Log
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
                    name = event.name,
                    nameError = !Validator.validateName(event.name).status
                )
            }
            
            is RegisterUIEvent.EmailChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    email = event.email,
                    emailError = !Validator.validateEmail(event.email).status
                )
            }
            
            is RegisterUIEvent.PasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    password = event.password,
                    passwordError = !Validator.validatePassword(event.password).status
                )
            }
    
            is RegisterUIEvent.ConfirmPasswordChanged -> {
                registerUIState.value = registerUIState.value.copy(
                    confirmPassword = event.confirmPassword,
                    confirmPasswordError = !Validator.validateConfirmPassword(registerUIState.value.password,event.confirmPassword).status
                )
            }
            
            is RegisterUIEvent.RegisterButtonClicked -> {
                register()
            }
        }
        validateRegisterUIDataWithRules()
    }
    
    private fun validateRegisterUIDataWithRules() {
        val nameValid = !registerUIState.value.nameError && registerUIState.value.name.isNotEmpty()
        val emailValid = !registerUIState.value.emailError && registerUIState.value.email.isNotEmpty()
        val passwordValid = !registerUIState.value.passwordError && registerUIState.value.password.isNotEmpty()
        val confirmPasswordValid = !registerUIState.value.confirmPasswordError && !registerUIState.value.confirmPassword.isEmpty()
        
        isAllValidationsPassed.value = nameValid && emailValid && passwordValid && confirmPasswordValid
    }
    
}