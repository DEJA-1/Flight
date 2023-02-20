package com.example.flight.data.network.response.flight

data class Results(
    val result: Result,
    val status: String,
    val status_code: Int,
    val time: String
)