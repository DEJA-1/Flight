@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.flight.presentation.screen.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.R
import com.example.flight.presentation.screen.home.components.dialogUi.city.DialogUiCity
import com.example.flight.presentation.screen.home.components.dialogUi.date.DialogDatePicker
import com.example.flight.presentation.screen.home.components.dialogUi.filter.DialogUiFilter
import com.example.flight.presentation.screen.home.components.dialogUi.filter.DisableSection
import com.example.flight.presentation.screen.home.components.dialogUi.filter.DurationSection
import com.example.flight.presentation.screen.home.components.dialogUi.filter.SliderSection
import com.example.flight.presentation.screen.home.components.dialogUi.passenger.DialogUiPassenger
import com.example.flight.presentation.screen.home.components.dialogUi.sort.DialogUiSort
import com.example.flight.presentation.screen.home.components.dialogUi.stop.DialogUiStopInfo
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.ui.theme.spacing
import com.example.flight.util.isTextValid
import com.example.flight.util.loadPopularCities
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun MyDialog(
    openDialog: MutableState<Boolean>,
    param: String,
    text: MutableState<String> = mutableStateOf(""),
    commonViewModel: CommonViewModel,
    onDatePicked: (String) -> Unit,
    onDoneQuitClick: (String) -> Unit,
    onDoneClick: (String) -> Unit
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUi(
                openDialogCustom = openDialog,
                param = param,
                text,
                commonViewModel = commonViewModel,
                onDatePicked = onDatePicked,
                onDoneQuitClick = onDoneQuitClick
            ) { text ->
                onDoneClick(text)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialogUi(
    openDialogCustom: MutableState<Boolean> = mutableStateOf(false),
    param: String,
    text: MutableState<String>,
    viewModel: HomeViewModel = hiltViewModel(),
    commonViewModel: CommonViewModel,
    onDatePicked: (String) -> Unit,
    onDoneQuitClick: (String) -> Unit,
    onDoneClick: (String) -> Unit
) {

    val isDoneEnabled = remember {
        mutableStateOf(false)
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    if (param == "Date") {
        DialogDatePicker() { date ->
            onDatePicked(date)
        }
    } else {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 10.dp),
            elevation = 8.dp,
            backgroundColor = MaterialTheme.colors.surface
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when (param) {
                    "Departure", "Arrival" -> {
                        DialogUiCity(
                            text,
                            keyboardController,
                            isDoneEnabled,
                            viewModel.loading.collectAsState().value,
                            onDoneClick
                        )
                    }
                    "Passengers" -> {
                        DialogUiPassenger(
                            isDoneEnabled
                        )
                    }
                    "Sort by" -> {
                        DialogUiSort(
                            isDoneEnabled
                        )
                    }
                    "Filter" -> {
                        DialogUiFilter(
                            isDoneEnabled
                        )
                    }

                    "Stop info" -> {
                        DialogUiStopInfo(
                            isDoneEnabled,
                            commonViewModel.currentItinerary.value.sliceData!!.slice.flightData.flights
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                enabled = isDoneEnabled.value
                            ) {
                                openDialogCustom.value = false
                                onDoneQuitClick(param)
                            }
                            .padding(top = 10.dp)
                            .background(
                                if (isDoneEnabled.value)
                                    MaterialTheme.colors.primary
                                else
                                    MaterialTheme.colors.secondary
                            ),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        Text(
                            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                            text = "Done",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onBackground
                        )

                    }
                }

            }

        }
    }
}





