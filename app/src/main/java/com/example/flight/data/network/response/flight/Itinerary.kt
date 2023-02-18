package com.example.flight.data.network.response.flight

data class Itinerary(
    val baggage_carrier: BaggageCarrier,
    val is_fused: Boolean,
    val opaque: Boolean,
    val price_details: PriceDetails,
    val slice_data: SliceData,
    val void_window_close: Boolean
)