package com.example.trashure.ui.screen.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.model.News
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailNewsViewModel (
    private val repository: TrashureRepository
) : ViewModel() {

    private val _newsState: MutableStateFlow<UiState<News>> =
        MutableStateFlow(UiState.Loading)

    val newsState: StateFlow<UiState<News>>
        get() = _newsState

    fun getNewsById(iPhoneId: Long) {
        viewModelScope.launch {
            _newsState.value = UiState.Loading
            _newsState.value = UiState.Success(repository.getNewsById(iPhoneId))
        }
    }

}