package com.example.flight.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.navigation.Screen
import com.example.flight.presentation.screen.home.components.FlightListSection
import com.example.flight.presentation.screen.home.components.TopSection
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.util.convertTimeToHours
import com.example.flight.util.filterFlights
import com.example.flight.util.sortFlights

@Composable
fun HomeScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel = viewModel(),
    viewModel: HomeViewModel,
    commonViewModel: CommonViewModel,
) {
    val flights = viewModel.flightData.value.result?.itineraryData?.toModel()?.itineraries
    val criteria = viewModel.selectedSort.value
    val flightParams = viewModel.filterParams.value
    val filteredFlights = filterFlights(flightParams, flights)

    val sortedAndFilteredFlights = remember(criteria, filteredFlights) {
        sortFlights(criteria, filteredFlights)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopSection(themeViewModel = themeViewModel, viewModel = viewModel)

            when (viewModel.loadingFlights.collectAsState().value) {
                true -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    if (sortedAndFilteredFlights.isNullOrEmpty())
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No flights found for\ngiven parameters",
                                color = MaterialTheme.colors.secondary,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    else {
                        FlightListSection(
                            flightList = sortedAndFilteredFlights
                        ) { itinerary ->
                            commonViewModel.updateCurrentItinerary(itinerary)
                            navController.navigate(Screen.Info.route)
                        }
                    }
                }


            }
        }
    }
}

