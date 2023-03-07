package com.example.flight.presentation.viewModel

import com.example.flight.MainCoroutineRule
import com.example.flight.data.repository.FakeFlightDatabaseRepositoryImpl
import com.example.flight.domain.model.flight.ItineraryModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InfoViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: InfoViewModel
    private lateinit var repository: FakeFlightDatabaseRepositoryImpl

    @Before
    fun setup() {
        repository = FakeFlightDatabaseRepositoryImpl()
        viewModel = InfoViewModel(repository)
    }

    @Test
    fun `InfoViewModel insert itinerary to a database`() = runTest {

        val itinerary = ItineraryModel()
        val itineraries = repository.getAllItinerariesFromDb().first()

        viewModel.insertItineraryToDb(itinerary)

        assertThat(itineraries).contains(itinerary)

    }

    @After
    fun teardown() {

    }
}