package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.domain.model.FlightParams
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val repository: FlightDatabaseRepository
): ViewModel() {

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