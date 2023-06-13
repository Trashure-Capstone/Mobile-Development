package com.example.trashure.ui.screen.sell

import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class SellTrashUIState (
    val weight: Int = 0,
    val trashType: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val date: Date? = null,
    val time: Time? = null
    )