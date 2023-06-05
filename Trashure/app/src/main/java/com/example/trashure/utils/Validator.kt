package com.example.trashure.utils

import android.util.Patterns

object Validator {


    fun validateName(name: String): ValidationResult {
        return ValidationResult(
            (!name.isNullOrEmpty() && name.length >= 2)
        )

    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )
    }
    
    fun validateConfirmPassword(password: String, confirmPassword: String): ValidationResult {
        return ValidationResult(
            (!confirmPassword.isNullOrEmpty() && confirmPassword.length >= 4 && password == confirmPassword)
        )
    }
}

data class ValidationResult(
    val status: Boolean = false
)








