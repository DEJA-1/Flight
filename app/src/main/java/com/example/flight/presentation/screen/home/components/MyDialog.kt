@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.flight.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import com.example.flight.presentation.viewmodel.HomeViewModel
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
            backgroundColor = MaterialTheme.colors.background
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                when (param) {
                    "Departure", "Arrival" -> {
                        DialogUiCity(text, keyboardController, isDoneEnabled, onDoneClick)
                    }
                    "Passengers" -> {
                        DialogUiPassenger(
                            isDoneEnabled
                        )
                    }
                    "Sort by" -> {
                        Text(
                            text = "sort"
                        )
                    }
                    "Filter" -> {
                        Text(
                            text = "fil"
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

@Composable
private fun DialogUiPassenger(
    isDoneEnabled: MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val passengers = remember {
        mutableStateOf(1)
    }
    isDoneEnabled.value = true

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(MaterialTheme.spacing.small),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = passengers.value.toString(),
                fontSize = 64.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colors.onBackground
            )

            Row(

            ) {
                Card(
                    shape = CircleShape,
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            passengers.value++
                            viewModel.updatePassengers(passengers = passengers.value)
                        }
                        .padding(MaterialTheme.spacing.extraSmall)
                ) {
                    Icon(
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        imageVector = Icons.Filled.Add,
                        contentDescription = "+ icon",
                        tint = Color.Black
                    )
                }

                Card(
                    shape = CircleShape,
                    elevation = 4.dp,
                    backgroundColor = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            if (passengers.value > 1) passengers.value--
                            viewModel.updatePassengers(passengers.value)
                        }
                        .padding(MaterialTheme.spacing.extraSmall)
                ) {
                    Icon(
                        modifier = Modifier.padding(MaterialTheme.spacing.medium),
                        painter = painterResource(R.drawable.remove),
                        contentDescription = "- icon",
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun DialogDatePicker(
    viewModel: HomeViewModel = hiltViewModel(),
    onDatePicked: (String) -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()
    val pickedDate = remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate = remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(pickedDate.value)
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnClickOutside = true
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        buttons = {
            positiveButton(text = "Ok") {
                onDatePicked(formattedDate.value)
                viewModel.updateIsDialogOpen()
            }
            negativeButton(text = "Cancel") {
                viewModel.updateIsDialogOpen()
            }
        }
    ) {
        this.datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = {
                it > LocalDate.now()
            }
        ) { date ->
            pickedDate.value = date
        }
    }

    dateDialogState.show()

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogUiCity(
    text: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    isDoneEnabled: MutableState<Boolean>,
    onDoneClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(0.8f)
            .padding(MaterialTheme.spacing.small),
        contentAlignment = Alignment.TopCenter
    ) {

        Column {
            InputTextField(
                text = text,
                label = "Enter a city",
                onAction = KeyboardActions {
                    if (isTextValid(text.value)) {
                        keyboardController?.hide()

                        isDoneEnabled.value = true
                        onDoneClick(text.value)
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.airplane),
                    contentDescription = "airplane icon"
                )
            }

            Divider(
                thickness = 2.dp,
                color = MaterialTheme.colors.secondary,
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.large
                )
            )

            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(loadPopularCities().shuffled()) {
                    Text(
                        modifier = Modifier
                            .padding(
                                bottom = MaterialTheme.spacing.small,
                                start = MaterialTheme.spacing.small,
                                end = MaterialTheme.spacing.small
                            )
                            .clickable {
                                text.value = it
                            },
                        text = it,
                        color = MaterialTheme.colors.onBackground,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}
