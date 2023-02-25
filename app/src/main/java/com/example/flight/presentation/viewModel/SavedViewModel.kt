package com.example.flight.presentation.viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.domain.repository.FlightDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    private val repository: FlightDatabaseRepository
) : ViewModel() {


    private val _itinerariesFromDb = MutableStateFlow(listOf<ItineraryModel>())
    val itinerariesFromDb = _itinerariesFromDb

    init {
        getItinerariesFromDb()
    }

    fun getItinerariesFromDb() =
        viewModelScope.launch() {
            repository.getAllItinerariesFromDb().collect() {
                _itinerariesFromDb.value = it
            }
        }

    fun deleteItineraryFromDb(itinerary: ItineraryModel) =
        viewModelScope.launch {
            repository.deleteItineraryFromDb(itinerary)
        }

}