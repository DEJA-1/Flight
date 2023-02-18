package com.example.flight.data.network.response.flight

data class Brand(
    val brandAttributes: BrandAttributes,
    val brandId: String,
    val name: String,
    val tier: Int
)