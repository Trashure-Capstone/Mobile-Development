package com.example.trashure.ui.screen.sell

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.trashure.data.remote.response.SellTrashResponse

import com.example.trashure.data.repository.TrashureRepository
import com.example.trashure.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
                    .ofPattern("hh:mm:ss")
                    .format(time)
            )
        )
    }
    
}