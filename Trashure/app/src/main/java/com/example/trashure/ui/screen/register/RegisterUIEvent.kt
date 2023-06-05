package com.example.trashure.ui.screen.register

sealed class RegisterUIEvent{
    
    data class NameChanged(val name:String): RegisterUIEvent()
    data class EmailChanged(val email:String): RegisterUIEvent()
    data class PasswordChanged(val password: String) : RegisterUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterUIEvent()

    object RegisterButtonClicked : RegisterUIEvent()
}
