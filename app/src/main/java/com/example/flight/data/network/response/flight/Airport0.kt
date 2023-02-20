package com.example.flight.data.network.response.flight

data class Airport0(
    val city: String,
    val code: String,
    val country: String,
    val flight_dest_count: Int,
    val flight_orig_count: Int,
    val geo: Geo,
    val name: String,
    val state: String
)