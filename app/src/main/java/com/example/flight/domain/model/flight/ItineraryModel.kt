package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.PriceDetails
import com.example.flight.data.network.response.flight.SliceData

data class ItineraryModel(
    val priceDetails: PriceDetailsModel,
    val sliceData: SliceDataModel
)
