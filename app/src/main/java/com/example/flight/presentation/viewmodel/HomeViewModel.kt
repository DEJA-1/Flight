package com.example.flight.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flight.common.Resource
import com.example.flight.domain.model.Location
import com.example.flight.domain.repository.FlightLocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FlightLocationRepository
) : ViewModel() {

    val buttonList = listOf<String>("Departure", "Arrival", "Date", "Passengers")
    val buttonListFilter = listOf<String>("Sort by", "Filter")

    private val _location = mutableStateOf(Location())
    val location = _location

    private val _loading = mutableStateOf(false)
    val loading = _loading

    private val _error = mutableStateOf("")
    val error = _error

    private val _selectedButtonIndex = mutableStateOf(0)
    val selectedButtonIndex = _selectedButtonIndex

    fun updateSelectedButtonIndex(index: Int) {
        _selectedButtonIndex.value = index
    }

    fun getLocation(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loading.value = true
            val response = repository.getLocation(name)

            when (response) {
                is Resource.Success -> {
                    _location.value = response.data!!
                    _loading.value = false
                }
                is Resource.Loading -> {
                    _loading.value = true
                }
                is Resource.Error -> {
                    _error.value = response.message.toString()
                    _loading.value = false
                }
            }
        }
    }
}