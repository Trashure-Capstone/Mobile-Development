package com.example.trashure.utils

import android.util.Patterns

object Validator {


    fun validateName(name: String): ValidationResult {
        return if(name.isEmpty()) ValidationResult(true)
        else ValidationResult(
            (name.length >= 2)
        )

    }

    fun validateEmail(email: String): ValidationResult {
        return if(email.isEmpty()) ValidationResult(true)
        else ValidationResult(
            (Patterns.EMAIL_ADDRESS.matcher(email).matches())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return if (password.isEmpty()) ValidationResult(true)
        else ValidationResult((password.length >= 8))
        
    }
    
    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return if(confirmPassword.isEmpty()) ValidationResult(true)
        else ValidationResult(
            (confirmPassword.length >= 8 && password == confirmPassword)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)








