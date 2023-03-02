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
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.screen.home.components.dialog.*
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.spacing
import com.example.flight.util.updateIsDialogOpen

@Composable
fun TopSection(
    filterParametersState: FilterParametersState,
    isThemeSwitchChecked: MutableState<Boolean>,
    buttonNames: List<String>,
    selectedButtonIndex: Int,
    selectedButtonName: String,
    itineraryCount: Int,
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
                buttonNames = buttonNames,
                selectedButtonIndex = selectedButtonIndex
            ) { buttonIndex, buttonName ->
                onParamsUpperClicked(buttonIndex, buttonName)
//                viewModel.updateSelectedButtonIndex(index)
//                viewModel.updateIsDialogOpen()
//                viewModel.updateSelectedButtonName(name)
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {

                TopSectionBottom(
                    itineraryCount = itineraryCount,
                    buttonNames = buttonNames,
                    selectedButtonIndex = selectedButtonIndex,
                    onParamsBottomClicked = { buttonName -> onParamsBottomClicked(buttonName) }
                )

            }
        }


        if (isDialogOpen.value) {
            when (selectedButtonName) {
                "Departure" -> {
                    DialogCity(
                        openDialog = isDialogOpen,
                        onDoneQuitClick = {
                            isDialogOpen.value = false
                            viewModel.updateFlightSearch(cityDep = viewModel.location.value.cityCode.toString())
                        },
                        onDoneClick = { city -> viewModel.getLocation(city) })
                }
                "Arrival" -> {
                    DialogCity(
                        openDialog = isDialogOpen,
                        onDoneQuitClick = {
                            isDialogOpen.value = false
                            viewModel.updateFlightSearch(cityArr = viewModel.location.value.cityCode.toString())
                        }) { city ->
                        viewModel.getLocation(city)
                    }
                }
                "Date" -> {
                    DialogDate(openDialog = isDialogOpen) { date ->
                        viewModel.updateFlightSearch(date = date)
                    }
                }
                "Passengers" -> {
                    DialogPassengers(openDialog = isDialogOpen) {
                        isDialogOpen.value = false
                        viewModel.updateFlightSearch(pass = viewModel.passengers.value)
                    }
                }
                "Sort by" -> {
                    DialogSort(
                        openDialog = isDialogOpen,
                        selectedSort = viewModel.selectedSort,
                        onDoneQuitClick = { isDialogOpen.value = false }
                    ) { sort ->
                        viewModel.updateSelectedSort(sort)
                    }
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
        ) { _, name ->
            onParamsBottomClicked(name)
        }

    }
}
