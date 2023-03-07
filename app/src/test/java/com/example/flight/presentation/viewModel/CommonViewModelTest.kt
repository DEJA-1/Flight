package com.example.flight.presentation.viewModel

import com.example.flight.MainCoroutineRule
import com.example.flight.domain.model.FlightSearchParametersState
import com.example.flight.domain.model.flight.ItineraryModel
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CommonViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: CommonViewModel

    @Before
    fun setup() {
        viewModel = CommonViewModel()
    }

    @Test
    fun `CommonViewModel update current itinerary`() {
        viewModel.currentItinerary.value = ItineraryModel(itineraryId = 1)
        val itinerary2 = ItineraryModel(itineraryId = 2)

        viewModel.updateCurrentItinerary(itinerary2)

        assertThat(viewModel.currentItinerary.value).isEqualTo(itinerary2)
    }

    @Test
    fun `CommonViewModel update current flight search parameters`() {
        val state = viewModel.currentFlightSearchParametersState.value
        val departureTime = "05.03.2023"
        val locationDeparture = "Osaka"
        val locationArrival = "Warsaw"
        val passengers = 3

        val newState = FlightSearchParametersState(
            departureTime = departureTime,
            locationDeparture = locationDeparture,
            locationArrival = locationArrival,
            passengers = passengers
        )

        viewModel.updateCurrentFlightSearchParametersState(newState)

        assertThat(viewModel.currentFlightSearchParametersState.value).isEqualTo(newState)
    }

}