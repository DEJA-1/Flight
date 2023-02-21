package com.example.flight.util

fun convertTimeToHours(time: String): Int {
    val parts = time.split(":").map { it.toInt() }
    return parts[0] * 24 + parts[1] + parts[2] / 60
}