package com.example.trashure.ui.screen.sell

import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trashure.data.remote.response.SellTrashResponse
import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.ui.common.UiState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class SellTrashViewModel(
    private val repository: TrashureRepository
    ) : ViewModel() {
    
    // handling upload response
    private val _state: MutableStateFlow<UiState<SellTrashResponse>> = MutableStateFlow(UiState.Empty)
    val state : StateFlow<UiState<SellTrashResponse>> = _state
    
    //handling ui state
    val sellTrashUIState = mutableStateOf(SellTrashUIState())
    
    fun updateWeight(weight: Int){
        sellTrashUIState.value = sellTrashUIState.value.copy(
            weight = weight
        )
    }
    
    fun updateTrashType(type: String){
        sellTrashUIState.value = sellTrashUIState.value.copy(
            trashType = type
        )
    }
    
    fun updateCoordinate(lat: Double, lon: Double){
        sellTrashUIState.value = sellTrashUIState.value.copy(
            lat = lat,
            lon = lon
        )
    }
    
    fun updateDate(date: LocalDate){
        sellTrashUIState.value = sellTrashUIState.value.copy(
            date = Date.valueOf(
                DateTimeFormatter
                    .ofPattern("yyyy-MM-dd")
                    .format(date)
            )
        )
    }
    
    fun updateTime(time: LocalTime){
        sellTrashUIState.value = sellTrashUIState.value.copy(
            time = Time.valueOf(
                DateTimeFormatter
                    .ofPattern("HH:mm:ss")
                    .format(time)
            )
        )
    }
    
    fun sellTrash(
        latitude: RequestBody,
        longitude: RequestBody,
        date: RequestBody,
        time: RequestBody,
        weight: RequestBody,
        trashType: RequestBody,
        image: MultipartBody.Part
    ){
        _state.value = UiState.Loading
        viewModelScope.launch {
            repository.sellTrash(latitude,longitude,date,time,weight,trashType,image)
                .catch {
                    _state.value = UiState.Error(it.message.toString())
                }
                .collect{ response ->
                    _state.value = response
                }
        }
    }
    
}