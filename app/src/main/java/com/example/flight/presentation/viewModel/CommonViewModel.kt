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


class CommonViewModel(): ViewModel() {

    private val _currentItinerary = mutableStateOf(ItineraryModel())
    val currentItinerary = _currentItinerary

    private val _currentFlightSearchParametersState = MutableStateFlow(FlightSearchParametersState())
    val currentFlightSearchParametersState: StateFlow<FlightSearchParametersState> = _currentFlightSearchParametersState

    fun updateCurrentItinerary(itinerary: ItineraryModel) {
        _currentItinerary.value = itinerary
    }

    fun updateCurrentFlightSearchParametersState(flightSearchParameters: FlightSearchParametersState) {
        _currentFlightSearchParametersState.update { flightSearchParametersState ->
            flightSearchParametersState.copy(
                departureTime = flightSearchParameters.departureTime,
                locationDeparture = flightSearchParameters.locationDeparture,
                locationArrival = flightSearchParameters.locationArrival,
                passengers = flightSearchParameters.passengers
            )

        }
    }

}