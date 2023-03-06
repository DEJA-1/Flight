package com.example.flight.data

import com.example.flight.MainCoroutineRule
import com.example.flight.common.Resource
import com.example.flight.data.repository.FakeFlightRepositoryImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FlightApiTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: FakeFlightRepositoryImpl

    @Before
    fun setup() {
        repository = FakeFlightRepositoryImpl()
    }

    @Test
    fun `Api getLocation returns success`() = runTest {
        val cityCode = "NYC"

        val response = repository.getLocation(cityCode)
        println(response.data)
        val locationData = response.data?.first()

        assertThat(response).isInstanceOf(Resource.Success::class.java)
        assertThat(locationData?.cityName).isNotEmpty()
    }

    @Test
    fun `Api getLocation returns error`() = runTest {
        repository.setShouldReturnNetworkError(true)

        val cityCode = "NYC"

        val response = repository.getLocation(cityCode)

        assertThat(response).isInstanceOf(Resource.Error::class.java)
    }

    @Test
    fun `Api getFlights returns success`() = runTest {
        val date = "2023-03-03"
        val cityDeparture = "Warsaw"
        val cityArrival = "Paris"
        val passengers = 1

        val response = repository.getFlights(date, cityDeparture, cityArrival, passengers)

        assertThat(response).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `Api getFlights returns error`() = runTest {
        repository.setShouldReturnNetworkError(true)

        val date = "2023-03-03"
        val cityDeparture = "Warsaw"
        val cityArrival = "Paris"
        val passengers = 1

        val response = repository.getFlights(date, cityDeparture, cityArrival, passengers)

        assertThat(response).isInstanceOf(Resource.Error::class.java)
    }
}