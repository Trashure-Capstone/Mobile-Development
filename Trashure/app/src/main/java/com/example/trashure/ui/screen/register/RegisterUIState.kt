package com.example.trashure.ui.screen.register

data class RegisterUIState(
    var name                    : String = "",
    var email                   : String = "",
    var password                : String = "",
    var confirmPassword         : String = "",

    var nameError               : Boolean = false,
    var emailError              : Boolean = false,
    var passwordError           : Boolean = false,
    var confirmPasswordError    : Boolean = false
)
