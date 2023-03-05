package com.example.flight.presentation.viewModel

import com.example.flight.MainCoroutineRule
import com.example.flight.data.repository.FakeFlightDatabaseRepositoryImpl
import com.example.flight.domain.model.flight.ItineraryModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SavedViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: SavedViewModel
    private lateinit var repository: FakeFlightDatabaseRepositoryImpl

    @Before
    fun setup() {
        repository = FakeFlightDatabaseRepositoryImpl()
        viewModel = SavedViewModel(repository)
    }

    @Test
    fun `SavedViewModel get all itineraries from a database`() = runTest {
        val itinerary1 = ItineraryModel(itineraryId = 1)
        val itinerary2 = ItineraryModel(itineraryId = 2)

        repository.insertItineraryToDb(itinerary1)
        repository.insertItineraryToDb(itinerary2)

        viewModel.getItinerariesFromDb()

        assertThat(viewModel.itinerariesFromDb.value).containsExactly(itinerary1, itinerary2)

    }

    @Test
    fun `SavedViewModel delete itinerary from a database`() = runTest {
        val itinerary = ItineraryModel(itineraryId = 1)
        repository.insertItineraryToDb(itinerary)

        viewModel.getItinerariesFromDb()
        assertThat(viewModel.itinerariesFromDb.value).contains(itinerary)

        viewModel.deleteItineraryFromDb(itinerary)

        viewModel.getItinerariesFromDb()

        assertThat(viewModel.itinerariesFromDb.value).doesNotContain(itinerary)
    }


    @After
    fun teardown() {

    }

}