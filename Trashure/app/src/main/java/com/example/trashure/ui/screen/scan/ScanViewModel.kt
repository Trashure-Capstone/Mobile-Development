package com.example.trashure.ui.screen.scan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ScanViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    
    //handling state for scan response
    private val _scanState: MutableStateFlow<UiState<Boolean>> =
        MutableStateFlow(UiState.Empty)
    val scanState: StateFlow<UiState<Boolean>> = _scanState
    
    fun rescan(){
        _scanState.value = UiState.Empty
    }
    fun scan(file: File?){
        _scanState.value = UiState.Loading
        if(file!=null){
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )
            viewModelScope.launch {
                repository.scan(imageMultipart)
                    .catch {
                        _scanState.value = UiState.Error(it.message.toString())
                    }
                    .collect{
                            scanResponse ->
                        _scanState.value = scanResponse
                    }
            }
        } else{
            _scanState.value = UiState.Error("File tidak boleh kosong")
        }
        
    }
    
    fun onEvent(event: ScanUIEvent) {
        //TODO
    }
    
}