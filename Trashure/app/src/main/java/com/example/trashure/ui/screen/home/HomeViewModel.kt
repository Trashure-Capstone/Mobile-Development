package com.example.trashure.ui.screen.home

import android.graphics.Movie
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.model.News
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (
    private val repository: TrashureRepository
    ) : ViewModel() {

    private val _newsState: MutableStateFlow<UiState<List<News>>> = MutableStateFlow(UiState.Loading)
    val newsState: StateFlow<UiState<List<News>>>
        get() = _newsState

    fun getAllNews() {
        viewModelScope.launch {
            repository.getNewsList()
                .catch {
                    _newsState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _newsState.value = UiState.Success(orderRewards)
                }
        }
    }
}