package com.example.flight.domain.model.flight

import com.example.flight.data.network.response.flight.Airport
import com.example.flight.data.network.response.flight.Datetime
@kotlinx.serialization.Serializable
data class ArrivalModel(
    val airport: AirportModel,
    val datetime: DatetimeModel
)
