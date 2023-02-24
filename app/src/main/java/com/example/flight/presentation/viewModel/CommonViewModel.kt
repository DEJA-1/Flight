package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flight.domain.model.FlightParams
import com.example.flight.domain.model.flight.ItineraryModel

class CommonViewModel : ViewModel() {

    private val _currentItinerary = mutableStateOf(ItineraryModel())
    val currentItinerary = _currentItinerary

    private val _currentFlightParams = mutableStateOf(FlightParams())
    val currentFlightParams = _currentFlightParams

    fun updateCurrentItinerary(itinerary: ItineraryModel) {
        _currentItinerary.value = itinerary
    }

    fun updateCurrentFlightParams(flightParams: FlightParams) {
        _currentFlightParams.value = flightParams
    }

}