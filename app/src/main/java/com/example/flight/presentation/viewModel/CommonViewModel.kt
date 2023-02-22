package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.flight.domain.model.flight.ItineraryModel

class CommonViewModel : ViewModel() {

    private val _currentItinerary = mutableStateOf(ItineraryModel())
    val currentItinerary = _currentItinerary

    fun updateCurrentItinerary(itinerary: ItineraryModel) {
        _currentItinerary.value = itinerary
    }

}