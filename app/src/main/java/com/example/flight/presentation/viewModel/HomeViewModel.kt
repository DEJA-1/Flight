package com.example.flight.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.common.Resource
import com.example.flight.data.network.response.flight.toResultsModel
import com.example.flight.domain.model.FilterParams
import com.example.flight.domain.model.FlightParams
import com.example.flight.domain.model.Location
import com.example.flight.domain.model.flight.ResultsModel
import com.example.flight.domain.repository.FlightLocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FlightLocationRepository
) : ViewModel() {

    val buttonList = listOf("Departure", "Arrival", "Date", "Passengers")
    val buttonListFilter = listOf("Sort by", "Filter", "SAVE")

    private val _location = mutableStateOf(Location())
    val location = _location

    private val _flightData = mutableStateOf(ResultsModel())
    val flightData = _flightData

    private val _passengers = mutableStateOf(1)
    val passengers = _passengers

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loadingFlights = MutableStateFlow(false)
    val loadingFlights = _loadingFlights.asStateFlow()

    private val _error = mutableStateOf("")
    val error = _error

    private val _selectedButtonIndex = mutableStateOf(0)
    val selectedButtonIndex = _selectedButtonIndex

    private val _isDialogOpen = mutableStateOf(false)
    val isDialogOpen = _isDialogOpen

    private val _selectedButtonName = mutableStateOf("")
    val selectedButtonName = _selectedButtonName

    private val _selectedSort = mutableStateOf("")
    val selectedSort = _selectedSort

    private val _selectedDurationFilter = mutableStateOf("")
    val selectedDurationFilter = _selectedDurationFilter

    private val _flightSearch = mutableStateOf(FlightParams())
    val flightSearch = _flightSearch

    private val _filterParams = mutableStateOf(FilterParams())
    val filterParams = _filterParams

    init {
        getFlights()
    }

    fun updateFlightSearch(
        cityDep: String = "", cityArr: String = "", date: String = "", pass: Int = 0
    ) {
        if (cityDep != "")
            _flightSearch.value.locationDeparture = cityDep
        if (cityArr != "")
            _flightSearch.value.locationArrival = cityArr
        if (date != "")
            _flightSearch.value.departureTime = date
        if (pass != 0)
            _flightSearch.value.passengers = pass
        Log.d("TEST", flightSearch.value.toString())
    }

    fun updateSelectedButtonName(name: String) {
        _selectedButtonName.value = name
    }

    fun updatePassengers(passengers: Int) {
        _passengers.value = passengers
    }

    fun updateIsDialogOpen() {
        _isDialogOpen.value = !_isDialogOpen.value
    }

    fun updateSelectedButtonIndex(index: Int) {
        _selectedButtonIndex.value = index
    }

    fun updateSelectedSort(sort: String) {
        _selectedSort.value = sort
    }

    fun updateSelectedDurationFilter(filter: String) {
        _filterParams.value.duration.value = filter.replace("<", "").replace("h", "").toInt()
    }

    fun updateDisableNextDayArrivalsFilter(isChecked: Boolean) {
        _filterParams.value.disableNextDayArrivals.value = isChecked
    }

    fun updateSliderValue(value: Float) {
        _filterParams.value.maxPrice.value = value.toInt()
    }

    fun getLocation(name: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getLocation(name)

            when (response) {
                is Resource.Success -> {
                    _location.value = response.data!!.first()
                    _loading.value = false
                }
                is Resource.Loading -> {
                    _loading.value = true
                }
                is Resource.Error -> {
                    _error.value = response.message.toString()
                    Log.d("ErrorLocation", _error.value)
                    _loading.value = false
                }
            }
        }

    fun getFlights(
        date: String = flightSearch.value.departureTime,
        cityDep: String = flightSearch.value.locationDeparture ?: "WAW",
        cityArr: String = flightSearch.value.locationArrival ?: "PAR",
        passengers: Int = flightSearch.value.passengers
    ) = viewModelScope.launch(Dispatchers.IO) {
        _loadingFlights.value = true
        val response = repository.getFlights(date, cityDep, cityArr, passengers)

        when (response) {
            is Resource.Success -> {
                _flightData.value = response.data!!.getAirFlightDepartures?.results?.toResultsModel()!!
                _loadingFlights.value = false
            }
            is Resource.Loading -> {
                _loadingFlights.value = false
            }
            is Resource.Error -> {
                Log.d("ErrorFlight", response.message.toString())
                _loadingFlights.value = false
            }
        }
    }
}

