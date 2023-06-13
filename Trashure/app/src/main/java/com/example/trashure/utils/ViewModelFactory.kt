package com.example.trashure.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trashure.ui.screen.login.LoginViewModel
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.ui.screen.register.RegisterViewModel
import com.example.trashure.ui.screen.scan.ScanViewModel
import com.example.trashure.ui.screen.sell.SellTrashViewModel

class ViewModelFactory(private val repository: TrashureRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ScanViewModel::class.java)){
            return ScanViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SellTrashViewModel::class.java)){
            return SellTrashViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}