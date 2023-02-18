package com.example.flight.presentation.screen.home.components

import android.util.Log
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
import com.example.flight.common.AppColors
import com.example.flight.presentation.viewmodel.HomeViewModel
import com.example.flight.presentation.viewmodel.ThemeViewModel
import com.example.flight.ui.theme.spacing
import kotlinx.coroutines.delay

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
                isSave = false,
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

        if (viewModel.isDialogOpen.value)
            MyDialog(openDialog = viewModel.isDialogOpen,
                param = viewModel.selectedButtonName.value,
                onDatePicked = { date ->
                    viewModel.updateFlightSearch(date = date)
                },
                onDoneQuitClick = { param ->
                    when (param) {
                        "Departure" -> {
                            viewModel.updateFlightSearch(cityDep = viewModel.location.value.cityCode.toString())
                        }
                        "Arrival" -> {
                            viewModel.updateFlightSearch(cityArr = viewModel.location.value.cityCode.toString())
                        }
                        "Passengers" -> {
                            viewModel.updateFlightSearch(pass = viewModel.passengers.value)
                        }
                        "Sort by" -> {

                        }
                        "Filter" -> {

                        }
                    }
                }) { text ->
                when (viewModel.selectedButtonIndex.value) {
                    0, 1 -> {
                        viewModel.getLocation(text)
                    }
                    2 -> {
                        viewModel.updateFlightSearch(date = text)
                    }
                    3 -> {

                    }
                }
            }

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
                    append("${viewModel.flightData.value.getAirFlightDepartures?.results?.result?.itinerary_count} ") //number of flights after retrofit call
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
            isSave = true,
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
