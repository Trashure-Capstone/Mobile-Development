package com.example.trashure.ui.screen.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ScanViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    
    //handling state for scan response
    private val _scanState: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState.Empty)
    val scanState: StateFlow<UiState<Boolean>> = _scanState
    
    private fun scan(file: MultipartBody.Part){
        _scanState.value = UiState.Loading
        viewModelScope.launch {
            repository.scan(file)
                .catch {
                    _scanState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    scanResponse ->
                    _scanState.value = scanResponse
                }
        }
    }
    
    fun onEvent(event: ScanUIEvent) {
        //TODO
    }
    
}