package com.example.flight.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flight.R
import com.example.flight.common.Constants
import com.example.flight.navigation.Screen
import com.example.flight.presentation.screen.home.components.FlightListSection
import com.example.flight.presentation.screen.home.components.TopSection
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.spacing
import com.example.flight.util.filterFlights
import com.example.flight.util.sortFlights

@Composable
fun HomeScreen(
    navigateToInfoScreen: () -> Unit,
    navigateToSavedScreen: () -> Unit,
    themeViewModel: ThemeViewModel = viewModel(),
    viewModel: HomeViewModel,
    commonViewModel: CommonViewModel,
) {

    val filterParametersState by viewModel.filterParametersState.collectAsState()
    val flightSearchParametersState by viewModel.flightSearchParametersState.collectAsState()
    val flightsFromApiResponse by viewModel.flightsFromApiResponse.collectAsState()

    val buttonUiState by viewModel.buttonUiState.collectAsState()

    val loadingLocation by viewModel.loading.collectAsState()

    val itineraries = flightsFromApiResponse.result?.itineraryData?.toModel()?.itineraries
    val sortCriteria = viewModel.selectedSort.value
    val filteredItineraries = filterFlights(filterParametersState, itineraries)

    val sortedAndFilteredFlights = remember(sortCriteria, filteredItineraries) {
        sortFlights(sortCriteria, filteredItineraries)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .testTag(Constants.TEST_TAG_HOME_SCREEN)
    ) {

        if (viewModel.error.value != "") {
            Toast.makeText(LocalContext.current, viewModel.error.value, Toast.LENGTH_SHORT).show()
            viewModel.resetErrorMessageValue()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopSection(
                filterParametersState = filterParametersState,
                loadingLocation = loadingLocation,
                itineraryCount = flightsFromApiResponse.result?.itineraryCount ?: 0,
                buttonUiState = buttonUiState,
                isThemeSwitchChecked = themeViewModel.isDarkTheme,
                selectedSort = viewModel.selectedSort.value,
                updateSelectedSort = { sortName -> viewModel.updateSelectedSort(sortName) },
                getLocation = { cityName -> viewModel.getLocation(cityName) },
                updateFlightSearchDepartureTime = { date ->
                    viewModel.updateFlightSearchDepartureTime(
                        date = date
                    )
                },
                updateFlightSearchCityDeparture = {
                    viewModel.updateFlightSearchCityDeparture(
                        city = viewModel.location.value.cityCode ?: "WAR"
                    )
                },
                updateFlightSearchCityArrival = {
                    viewModel.updateFlightSearchCityArrival(
                        city = viewModel.location.value.cityCode ?: "PAR"
                    )
                },
                updateFlightSearchPassengersCount = { passengerCount ->
                    viewModel.updateFlightSearchPassengersCount(
                        passengers = passengerCount
                    )
                },
                onDisableNextDayArrivalsClicked = { isDisabled ->
                    viewModel.updateFilterDisableNextDayArrivals(
                        isDisabled
                    )
                },
                onDurationButtonClicked = { buttonName ->
                    viewModel.updateFilterMaxDuration(
                        buttonName
                    )
                },
                onThemeSwitchClicked = { themeViewModel.switchTheme() },
                onSliderValueChange = { valueFromSlider ->
                    viewModel.updateFilterMaxPrice(
                        valueFromSlider
                    )
                },
                onParamsUpperClicked = { buttonIndex, buttonName ->
                    viewModel.updateButtonUiStateSelectedButtonIndex(buttonIndex)
                    viewModel.updateButtonUiStateSelectedButtonName(buttonName)
                },
                onParamsBottomClicked = { buttonName ->
                    if (buttonName != "SAVE") {
                        viewModel.updateButtonUiStateSelectedButtonName(buttonName)
                    } else {
                        commonViewModel.updateCurrentFlightSearchParametersState(flightSearchParametersState)
                        viewModel.getFlights(flightSearchParametersState)
                    }
                }
            )


            when (viewModel.loadingFlights.collectAsState().value) {
                true -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                false -> {
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
                            modifier = Modifier
                                .testTag(Constants.TEST_TAG_FLIGHT_LAZY_COLUMN),
                            itineraries = sortedAndFilteredFlights,
                            isSaved = false,
                            onFlightClick = { itinerary ->
                                commonViewModel.updateCurrentItinerary(itinerary)
                                navigateToInfoScreen()
                            }
                        )
                    }
                }


            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MaterialTheme.spacing.small)
                .testTag(Constants.TEST_TAG_FAB),
            onClick = { navigateToSavedScreen() },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onBackground,
            elevation = FloatingActionButtonDefaults.elevation(4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.airplane),
                contentDescription = "Airplane icon"
            )
        }
    }
}

