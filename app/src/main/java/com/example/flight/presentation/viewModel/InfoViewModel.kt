package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.data.repository.FlightDatabaseRepositoryImpl
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val repository: FlightDatabaseRepository
): ViewModel(){

    fun addItineraryToDb(itinerary: ItineraryModel) =
        viewModelScope.launch {
            repository.addItineraryToDb(itinerary)
        }


}