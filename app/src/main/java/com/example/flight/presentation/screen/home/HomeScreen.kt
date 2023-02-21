package com.example.flight.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flight.presentation.screen.home.components.FlightListSection
import com.example.flight.presentation.screen.home.components.TopSection
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.util.convertTimeToHours
import com.example.flight.util.filterFlights
import com.example.flight.util.sortFlights

@Composable
fun HomeScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel = viewModel(),
    viewModel: HomeViewModel
) {

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

                    FlightListSection(
                        flightList = filterFlights(
                            data = sortFlights(
                                data = viewModel.flightData.value.result?.itineraryData?.toModel()?.itineraries,
                                criteria = viewModel.selectedSort.value
                            )!!, filterParams = viewModel.filterParams.value
                        )!!
                    ) {

                    }
                }


            }
        }
    }
}

