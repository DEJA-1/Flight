package com.example.flight.data.network.response.flight

data class Info(
    val aircraft: String,
    val aircraft_type: String,
    val bkg_class: String,
    val brand_id: String,
    val cabin_class: String,
    val cabin_name: String,
    val carrier_locator: String,
    val disinsection: Boolean,
    val duration: String,
    val flight_number: String,
    val id: Int,
    val marketing_airline: String,
    val marketing_airline_code: String,
    val notes: Notes,
    val operating_airline: String,
    val operating_airline_code: String,
    val premium_seating_flag: Boolean,
    val seat_free_assignment: Boolean,
    val seat_map_available: Boolean,
    val seat_selection_allowed: Boolean,
    val stop_count: Int
)