package com.example.flight.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: FlightDatabaseRepository
): ViewModel(){

    fun insertItineraryToDb(itinerary: ItineraryModel) =
        viewModelScope.launch {
            repository.insertItineraryToDb(itinerary)
        }
}