package com.example.flight.data.local.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.flight.domain.model.flight.ItineraryModel
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named


@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@HiltAndroidTest
class FlightDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_database")
    lateinit var database: FlightDatabase
    private lateinit var dao: FlightDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.flightDao
    }

    @Test
    fun insertItineraryToDatabase() = runTest {
        val itinerary = ItineraryModel(itineraryId = 1)
        dao.insertItineraryToDb(itinerary)

        val itineraries = dao.getAllItinerariesFromDb().first()

        assertThat(itineraries).contains(itinerary)
    }

     fun deleteItineraryFromDatabase() = runTest {

        val itinerary = ItineraryModel(itineraryId = 1)
        dao.insertItineraryToDb(itinerary)
        dao.deleteItineraryFromDb(itinerary)

        val itineraries = dao.getAllItinerariesFromDb().first()

        assertThat(itineraries).doesNotContain(itinerary)
    }

    @After
    fun teardown() {
        database.close()
    }
}