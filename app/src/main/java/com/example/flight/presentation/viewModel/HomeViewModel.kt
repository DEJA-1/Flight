package com.example.flight.presentation.viewModel

import android.text.TextUtils.replace
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.common.Resource
import com.example.flight.data.network.response.flight.toResultsModel
import com.example.flight.data.network.response.location.LocationResponse
import com.example.flight.domain.model.FilterParametersState
import com.example.flight.domain.model.FlightSearchParametersState
import com.example.flight.data.network.response.location.toLocation
import com.example.flight.domain.model.flight.ResultsModel
import com.example.flight.domain.model.location.Location
import com.example.flight.domain.repository.FlightLocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FlightLocationRepository
) : ViewModel() {

    private val _filterParametersState = MutableStateFlow(FilterParametersState())
    val filterParametersState: StateFlow<FilterParametersState> = _filterParametersState

    private val _flightSearchParametersState = MutableStateFlow(FlightSearchParametersState())
    val flightSearchParametersState: StateFlow<FlightSearchParametersState> = _flightSearchParametersState

    fun updateFilterMaxPrice(priceValueFromSlider: Float) {
        _filterParametersState.update { filterParametersState ->
            filterParametersState.copy(maxPrice = priceValueFromSlider.toInt())
        }
    }

    fun updateFilterMaxDuration(selectedFilter: String) {
        val maxDuration = selectedFilter.replace("<", "").replace("h", "").toInt()
        _filterParametersState.update {filterParametersState ->
            filterParametersState.copy(maxDuration = maxDuration)
        }
    }

    fun updateFilterDisableNextDayArrivals(isDisabled: Boolean) {
        _filterParametersState.update { filterParametersState ->
            filterParametersState.copy(disableNextDayArrivals = isDisabled)
        }
    }

    fun updateFlightSearchCityDeparture(city: String) {
        _flightSearchParametersState.update {  flightSearchParametersState ->
            flightSearchParametersState.copy(locationDeparture = city)
        }
        Log.d("flightSearchParameters: ", _flightSearchParametersState.value.toString())
    }

    fun updateFlightSearchCityArrival(city: String) {
        _flightSearchParametersState.update { flightSearchParametersState ->
            flightSearchParametersState.copy(locationArrival = city)
        }
    }

    fun updateFlightSearchDepartureTime(date: String) {
        _flightSearchParametersState.update { flightSearchParametersState ->
            flightSearchParametersState.copy(departureTime = date)
        }
    }

    fun updateFlightSearchPassengersCount(passengers: Int) {
        _flightSearchParametersState.update { flightSearchParametersState ->
            flightSearchParametersState.copy(passengers = passengers)
        }
    }

    private val _selectedButtonIndex = mutableStateOf(0)
    val selectedButtonIndex = _selectedButtonIndex

    // ------------------------- OLD

    val buttonNames = listOf("Departure", "Arrival", "Date", "Passengers")
    val buttonNamesFilters = listOf("Sort by", "Filter", "SAVE")

    private val _location = mutableStateOf(LocationResponse())
    val location: State<LocationResponse> = _location

//    private val _flightData = mutableStateOf(ResultsModel())
//    val flightData = _flightData

    private val _flightsFromApiResponse = MutableStateFlow(ResultsModel())
    val flightsFromApiResponse: StateFlow<ResultsModel> = _flightsFromApiResponse

    private val _passengers = mutableStateOf(1)
    val passengers = _passengers

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    private val _loadingFlights = MutableStateFlow(false)
    val loadingFlights = _loadingFlights.asStateFlow()


    private val _selectedButtonName = mutableStateOf("")
    val selectedButtonName = _selectedButtonName

    private val _selectedSort = mutableStateOf("")
    val selectedSort = _selectedSort


//    private val _flightSearch = mutableStateOf(FlightSearchParametersState())
//    val flightSearch = _flightSearch

    private val _filterParams = mutableStateOf(FilterParametersState())
    val filterParams = _filterParams

    init {
        getFlights()
    }


    fun updateSelectedButtonName(name: String) {
        _selectedButtonName.value = name
    }
    fun updateSelectedButtonIndex(index: Int) {
        _selectedButtonIndex.value = index
    }

    fun updateSelectedSort(sort: String) {
        _selectedSort.value = sort
    }

    fun resetErrorMessageValue() {
        _error.value = ""
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

    fun getFlights(date: String = _flightSearchParametersState.value.departureTime,
        cityDep: String = _flightSearchParametersState.value.locationDeparture ?: "WAW",
        cityArr: String = _flightSearchParametersState.value.locationArrival ?: "PAR",
        passengers: Int = _flightSearchParametersState.value.passengers
    ) = viewModelScope.launch(Dispatchers.IO) {
        _loadingFlights.value = true
        val response = repository.getFlights(date, cityDep, cityArr, passengers)

        when (response) {
            is Resource.Success -> {
                _flightsFromApiResponse.emit(response.data!!.getAirFlightDepartures.results.toResultsModel())
                _loadingFlights.value = false
            }
            is Resource.Loading -> {
                _loadingFlights.value = false
            }
            is Resource.Error -> {
                _error.value = response.message.toString()
                Log.d("ErrorFlight", response.message.toString())
                _loadingFlights.value = false
            }
        }
    }
}

