package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flight.domain.model.FlightSearchParametersState
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val repository: FlightDatabaseRepository
): ViewModel() {

    private val _currentItinerary = mutableStateOf(ItineraryModel())
    val currentItinerary = _currentItinerary

    private val _currentFlightParams = MutableStateFlow(FlightSearchParametersState())
    val currentFlightParams: StateFlow<FlightSearchParametersState> = _currentFlightParams

    fun updateCurrentItinerary(itinerary: ItineraryModel) {
        _currentItinerary.value = itinerary
    }

    fun updateCurrentFlightParams(flightSearchParameters: FlightSearchParametersState) {
        _currentFlightParams.update { flightSearchParametersState ->
            flightSearchParametersState.copy(
                departureTime = flightSearchParameters.departureTime,
                locationDeparture = flightSearchParameters.locationDeparture,
                locationArrival = flightSearchParameters.locationArrival,
                passengers = flightSearchParameters.passengers
            )

        }
    }

}