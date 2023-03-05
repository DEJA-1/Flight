package com.example.flight.presentation.viewModel

import com.example.flight.MainCoroutineRule
import com.example.flight.data.repository.FakeFlightRepositoryImpl
import com.google.common.truth.Truth
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(FakeFlightRepositoryImpl())
    }

    // TODO getLocation

    // TODO getFlights

    @Test
    fun `HomeViewModel update filter max price`() {
        val newMaxPrice = 5.5f

        viewModel.updateFilterMaxPrice(newMaxPrice)
        val state = viewModel.filterParametersState.value

        Truth.assertThat(state.maxPrice).isEqualTo(newMaxPrice.toInt())
    }

    @Test
    fun `HomeViewModel update filter max duration`() {
        val newMaxDuration = "5"

        viewModel.updateFilterMaxDuration(newMaxDuration)
        val state = viewModel.filterParametersState.value

        Truth.assertThat(state.maxDuration).isEqualTo(newMaxDuration.toInt())
    }

    @Test
    fun `HomeViewModel update filter disable next day arrivals disabled`() {
        val isDisabled = false

        viewModel.updateFilterDisableNextDayArrivals(isDisabled)
        val state = viewModel.filterParametersState.value

        Truth.assertThat(state.disableNextDayArrivals).isEqualTo(false)
    }

    @Test
    fun `HomeViewModel update filter disable next day arrivals enabled`() {
        val isDisabled = true

        viewModel.updateFilterDisableNextDayArrivals(isDisabled)
        val state = viewModel.filterParametersState.value

        Truth.assertThat(state.disableNextDayArrivals).isEqualTo(true)
    }

    @Test
    fun `HomeViewModel update flight search departure city`() {
        val newCity = "Wroclaw"

        viewModel.updateFlightSearchCityDeparture(newCity)
        val state = viewModel.flightSearchParametersState.value

        Truth.assertThat(state.locationDeparture).isEqualTo(newCity)
    }

    @Test
    fun `HomeViewModel update flight search arrival city`() {
        val newCity = "Wroclaw"

        viewModel.updateFlightSearchCityArrival(newCity)
        val state = viewModel.flightSearchParametersState.value

        Truth.assertThat(state.locationArrival).isEqualTo(newCity)
    }

    @Test
    fun `HomeViewModel update flight search departure time`() {
        val newDepartureTime = "05.03.2023"

        viewModel.updateFlightSearchDepartureTime(newDepartureTime)
        val state = viewModel.flightSearchParametersState.value

        Truth.assertThat(state.departureTime).isEqualTo(newDepartureTime)
    }

    @Test
    fun `HomeViewModel update flight search passengers count`() {
        val newPassengersCount = 3

        viewModel.updateFlightSearchPassengersCount(newPassengersCount)
        val state = viewModel.flightSearchParametersState.value

        Truth.assertThat(state.passengers).isEqualTo(newPassengersCount)
    }

    @Test
    fun `HomeViewModel update selected button name`() {
        val newSelectedButtonName = "Button"

        viewModel.updateButtonUiStateSelectedButtonName(newSelectedButtonName)
        val buttonState = viewModel.buttonUiState.value

        Truth.assertThat(buttonState.selectedButtonName).isEqualTo(newSelectedButtonName)
    }

    @Test
    fun `HomeViewModel update selected button index`() {
        val newSelectedButtonIndex = 4

        viewModel.updateButtonUiStateSelectedButtonIndex(newSelectedButtonIndex)
        val buttonState = viewModel.buttonUiState.value

        Truth.assertThat(buttonState.selectedButtonIndex).isEqualTo(newSelectedButtonIndex)
    }

    @Test
    fun `HomeViewModel update selected sort`() {
        val newSort = "Duration"

        viewModel.updateSelectedSort(newSort)
        val selectedSort = viewModel.selectedSort.value

        Truth.assertThat(selectedSort).isEqualTo(newSort)
    }

    @Test
    fun `HomeViewModel reset error message`() {
        viewModel.resetErrorMessageValue()

        Truth.assertThat(viewModel.error.value).isEqualTo("")
    }
}