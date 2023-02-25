package com.example.flight.domain.model.flight

@kotlinx.serialization.Serializable
data class PriceDetailsModel(
    val totalPerTicket: Double,
    val total: Double
)
