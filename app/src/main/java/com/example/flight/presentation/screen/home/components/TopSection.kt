package com.example.flight.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight.presentation.screen.home.components.dialog.*
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun TopSection(
    themeViewModel: ThemeViewModel,
    viewModel: HomeViewModel
) {
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
                themeViewModel = themeViewModel
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
                buttonList = viewModel.buttonList,
                selectedButtonIndex = viewModel.selectedButtonIndex
            ) { index, name ->
                viewModel.updateSelectedButtonIndex(index)
                viewModel.updateIsDialogOpen()
                viewModel.updateSelectedButtonName(name)
            }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {

                TopSectionBottom(viewModel)

            }
        }

        if (viewModel.isDialogOpen.value) {
            when (viewModel.selectedButtonName.value) {
                "Departure" -> {
                    DialogCity(
                        openDialog = viewModel.isDialogOpen,
                        onDoneQuitClick = {
                            viewModel.isDialogOpen.value = false
                            viewModel.updateFlightSearch(cityDep = viewModel.location.value.cityCode.toString())
                        }) { city ->
                        viewModel.getLocation(city)
                    }
                }
                "Arrival" -> {
                    DialogCity(
                        openDialog = viewModel.isDialogOpen,
                        onDoneQuitClick = {
                            viewModel.isDialogOpen.value = false
                            viewModel.updateFlightSearch(cityArr = viewModel.location.value.cityCode.toString())
                        }) { city ->
                        viewModel.getLocation(city)
                    }
                }
                "Date" -> {
                    DialogDate(openDialog = viewModel.isDialogOpen) { date ->
                        viewModel.updateFlightSearch(date = date)
                    }
                }
                "Passengers" -> {
                    DialogPassengers(openDialog = viewModel.isDialogOpen) {
                        viewModel.updateFlightSearch(pass = viewModel.passengers.value)
                        viewModel.isDialogOpen.value = false
                    }
                }
                "Sort by" -> {
                    DialogSort(
                        openDialog = viewModel.isDialogOpen,
                        selectedSort = viewModel.selectedSort,
                        onDoneQuitClick = { viewModel.isDialogOpen.value = false }
                    ) { sort ->
                        viewModel.updateSelectedSort(sort)
                    }
                }
                "Filter" -> {
                    DialogFilter(openDialog = viewModel.isDialogOpen) {
                        viewModel.isDialogOpen.value = false
                    }
                }
            }
        }

//            MyDialog(openDialog = viewModel.isDialogOpen,
//                param = viewModel.selectedButtonName.value,
//                onDatePicked = { date ->
//                    viewModel.updateFlightSearch(date = date)
//                },
//                onDoneQuitClick = { param ->
//                    when (param) {
//                        "Departure" -> {
//                            viewModel.updateFlightSearch(cityDep = viewModel.location.value.cityCode.toString())
//                        }
//                        "Arrival" -> {
//                            viewModel.updateFlightSearch(cityArr = viewModel.location.value.cityCode.toString())
//                        }
//                        "Passengers" -> {
//                            viewModel.updateFlightSearch(pass = viewModel.passengers.value)
//                        }
//                    }
//                }) { text ->
//                when (viewModel.selectedButtonIndex.value) {
//                    0, 1 -> {
//                        viewModel.getLocation(text)
//                    }
//                    2 -> {
//                        viewModel.updateFlightSearch(date = text)
//                    }
//                    3 -> {
//
//                    }
//                }
//            }

    }
}

@Composable
private fun TopSectionBottom(viewModel: HomeViewModel) {
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
                    append("${viewModel.flightData.value.result?.itineraryCount} ") //number of flights after retrofit call
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
            buttonList = viewModel.buttonListFilter,
            selectedButtonIndex = viewModel.selectedButtonIndex
        ) { index, name ->
            if (name != "SAVE") {
                viewModel.updateIsDialogOpen()
                viewModel.updateSelectedButtonName(name)
            } else {
                viewModel.getFlights()
            }

        }

    }
}
