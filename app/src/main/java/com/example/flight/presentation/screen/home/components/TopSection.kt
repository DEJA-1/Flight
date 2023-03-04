package com.example.flight.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight.domain.model.FilterParametersState
import com.example.flight.presentation.screen.common_components.Header
import com.example.flight.presentation.screen.home.components.dialog.*
import com.example.flight.ui.theme.spacing
import com.example.flight.util.updateIsDialogOpen

@Composable
fun TopSection(
    filterParametersState: FilterParametersState,
    isThemeSwitchChecked: MutableState<Boolean>,
    buttonUiState: ButtonUiState,
    itineraryCount: Int,
    selectedSort: String,
    updateSelectedSort: (String) -> Unit,
    getLocation: (String) -> Unit,
    updateFlightSearchPassengersCount: (Int) -> Unit,
    updateFlightSearchDepartureTime: (String) -> Unit,
    updateFlightSearchCityDeparture: () -> Unit,
    updateFlightSearchCityArrival: () -> Unit,
    onDisableNextDayArrivalsClicked: (Boolean) -> Unit,
    onDurationButtonClicked: (String) -> Unit,
    onThemeSwitchClicked: () -> Unit,
    onSliderValueChange: (Float) -> Unit,
    onParamsUpperClicked: (Int, String) -> Unit,
    onParamsBottomClicked: (String) -> Unit,
) {

    val isDialogOpen = remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.26f)
            .shadow(elevation = 4.dp)
            .background(MaterialTheme.colors.surface)
            .padding(MaterialTheme.spacing.small)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(
                isChecked = isThemeSwitchChecked,
                isSwitchThemeButtonVisible = true,
                onThemeSwitchClicked = { onThemeSwitchClicked() }
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 4.dp),
                thickness = 2.dp,
                color = MaterialTheme.colors.secondary
            )

            ParamsSection(
                isFilter = false,
                buttonNames = buttonUiState.buttonNames,
                selectedButtonIndex = buttonUiState.selectedButtonIndex
            ) { buttonIndex, buttonName ->
                onParamsUpperClicked(buttonIndex, buttonName)
                updateIsDialogOpen(isDialogOpen)
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {

                TopSectionBottom(
                    itineraryCount = itineraryCount,
                    buttonNames = buttonUiState.buttonNamesFilters,
                    isDialogOpen = isDialogOpen,
                    selectedButtonIndex = buttonUiState.selectedButtonIndex,
                    onParamsBottomClicked = { buttonName -> onParamsBottomClicked(buttonName) }
                )

            }
        }


        if (isDialogOpen.value) {
            when (buttonUiState.selectedButtonName) {
                "Departure" -> {
                    DialogCity(
                        openDialog = isDialogOpen,
                        onDoneQuitClick = {
                            updateFlightSearchCityDeparture()
                            updateIsDialogOpen(isDialogOpen)
                        },
                        onDoneClick = { city -> getLocation(city) })
                }
                "Arrival" -> {
                    DialogCity(
                        openDialog = isDialogOpen,
                        onDoneQuitClick = {
                            updateIsDialogOpen(isDialogOpen)
                            updateFlightSearchCityArrival()
                        },
                        onDoneClick = { city -> getLocation(city) })
                }
                "Date" -> {
                    DialogDate(
                        openDialog = isDialogOpen,
                        onCancelDateClicked = { isDialogOpen.value = false },
                        onDatePicked = { date ->
                            updateFlightSearchDepartureTime(date)
                            updateIsDialogOpen(isDialogOpen)
                        }
                    )
                }
                "Passengers" -> {
                    DialogPassengers(openDialog = isDialogOpen,
                        onDoneQuitClick = {
                            updateIsDialogOpen(isDialogOpen)
                        },
                        onPassengerButtonClicked = { passengersCount ->
                            updateFlightSearchPassengersCount(passengersCount)
                        })
                }
                "Sort by" -> {
                    DialogSort(
                        openDialog = isDialogOpen,
                        selectedSort = selectedSort,
                        onDoneQuitClick = { updateIsDialogOpen(isDialogOpen) },
                        onSortClick = { sortName -> updateSelectedSort(sortName) }
                    )
                }
                "Filter" -> {
                    DialogFilter(
                        openDialog = isDialogOpen,
                        filterParametersState = filterParametersState,
                        onDisableNextDayArrivalsClicked = onDisableNextDayArrivalsClicked,
                        onSliderValueChange = onSliderValueChange,
                        onDurationButtonClicked = onDurationButtonClicked,
                        onDoneQuitClick = { updateIsDialogOpen(isDialogOpen) }
                    )
                }
            }
        }


    }
}

@Composable
private fun TopSectionBottom(
    itineraryCount: Int,
    isDialogOpen: MutableState<Boolean>,
    buttonNames: List<String>,
    selectedButtonIndex: Int,
    onParamsBottomClicked: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp
                    )
                ) {
                    append("$itineraryCount ") //number of flights after retrofit call
                }

                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Normal,
                        fontSize = 32.sp
                    )
                ) {
                    append("flights")
                }
            }
        )

        ParamsSection(
            isFilter = true,
            modifier = Modifier.padding(top = 10.dp, bottom = 0.dp, end = 0.dp),
            buttonNames = buttonNames,
            selectedButtonIndex = selectedButtonIndex
        ) { _, buttonName ->
            onParamsBottomClicked(buttonName)
            if (buttonName != "SAVE")
                updateIsDialogOpen(isDialogOpen)
        }

    }
}
