package com.example.trashure.ui.screen.scan

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trashure.data.remote.response.ScanResult

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
    private val _scanState: MutableStateFlow<UiState<ScanResult>> =
        MutableStateFlow(UiState.Empty)
    val scanState: StateFlow<UiState<ScanResult>> = _scanState
    
    fun rescan(){
        _scanState.value = UiState.Empty
    }
    fun scan(file: File?,context: Context){
        _scanState.value = UiState.Loading
        if(file!=null){
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )
            viewModelScope.launch {
                repository.scan(imageMultipart,context)
                    .catch {
                        _scanState.value = UiState.Error(it.message.toString())
                    }
                    .collect{
                            scanResponse ->
                        _scanState.value = scanResponse
                        Log.d("ScanResponse",scanResponse.toString())
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