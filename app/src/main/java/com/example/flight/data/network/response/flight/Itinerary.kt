package com.example.flight.data.network.response.flight

data class Itinerary(
    val baggage_carrier: BaggageCarrier,
    val contract_page_url: String,
    val displayable_airlines: DisplayableAirlines,
    val is_fused: Any,
    val opaque: Boolean,
    val ppn_contract_bundle: String,
    val ppn_seat_bundle: String,
    val price_details: PriceDetails,
    val slice_data: SliceData,
    val void_window_close: Any
)