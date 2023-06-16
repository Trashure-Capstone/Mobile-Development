package com.example.trashure.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trashure.ui.screen.login.LoginViewModel
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.ui.screen.home.HomeViewModel
import com.example.trashure.ui.screen.news.DetailNewsViewModel
import com.example.trashure.ui.screen.profile.ProfileViewModel
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
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailNewsViewModel::class.java)) {
            return DetailNewsViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}