package com.example.flight.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun checkIfArrivesNextDay(date1: String, date2: String) : Boolean{
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateStart = LocalDate.parse(date1)
    val dateEnd = LocalDate.parse(date2)

    return dateEnd.isAfter(dateStart)
}