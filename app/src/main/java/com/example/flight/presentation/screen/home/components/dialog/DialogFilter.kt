package com.example.flight.presentation.screen.home.components.dialog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.domain.model.FilterParametersState
import com.example.flight.presentation.screen.common_components.DoneButton
import com.example.flight.presentation.screen.common_components.CustomSwitch
import com.example.flight.presentation.screen.home.components.ParamsSection
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun DialogFilter(
    openDialog: MutableState<Boolean>,
    filterParametersState: FilterParametersState,
    onSliderValueChange: (Float) -> Unit,
    onDoneQuitClick: () -> Unit,
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUiFilter(
                filterParametersState = filterParametersState,
                onSliderValueChange = onSliderValueChange,
                onDoneQuitClick = onDoneQuitClick
            )
        }
    }
}

@Composable
fun CustomDialogUiFilter(
    filterParametersState: FilterParametersState,
    onSliderValueChange: (Float) -> Unit,
    onDoneQuitClick: () -> Unit,
) {
    FilterDialogUi(
        filterParametersState = filterParametersState,
        onSliderValueChange = onSliderValueChange,
        onDoneQuitClick = onDoneQuitClick
    )
}

@Composable
fun FilterDialogUi(
    filterParametersState: FilterParametersState,
    onSliderValueChange: (Float) -> Unit,
    onDoneQuitClick: () -> Unit,
) {

    val isDoneEnabled = remember {
        mutableStateOf(true)
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(MaterialTheme.spacing.small)
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                SliderSection(
                    filterParameterMaxPrice = filterParametersState.maxPrice.toFloat(),
                    onSliderValueChange = onSliderValueChange
                )

                Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.small
                    )
                )

                DurationSection(

                )

                Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.medium
                    )
                )

                DisableSection(

                )
            }
        }

        DoneButton(
            isDoneEnabled = isDoneEnabled) {
            onDoneQuitClick()
        }
    }
}

@Composable
fun DisableSection(

) {

    val isChecked = remember {
        mutableStateOf(viewModel.filterParams.value.disableNextDayArrivals.value)
    }

    Row(horizontalArrangement = Arrangement.SpaceBetween) {

        Box(
            modifier = Modifier.fillMaxWidth(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Disable next day arrivals",
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }

        CustomSwitch(isChecked = isChecked) {
            isChecked.value = !isChecked.value
            viewModel.updateDisableNextDayArrivalsFilter(isChecked.value)
        }

    }

}

@Composable
fun DurationSection(

) {

    val selectedButtonIndex = remember {
        mutableStateOf(-1)
    }

    ParamsSection(
        buttonList = listOf("<5h", "<15h", "<30h", "<50h"),
        isFilter = false,
        selectedButtonIndex = selectedButtonIndex,
    ) { index, name ->
        selectedButtonIndex.value = index
        viewModel.updateSelectedDurationFilter(name)
    }
}0

@Composable
fun SliderSection(
    filterParameterMaxPrice: Float,
    onSliderValueChange: (Float) -> Unit
) {

    val sliderValue = remember {
        mutableStateOf(filterParameterMaxPrice)
    }

    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = sliderValue.value,
        onValueChange = { newValue ->
            sliderValue.value = newValue
            onSliderValueChange(sliderValue.value)
        },
        valueRange = 0f..10000f
    )

    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            ) {
                append("Max price: ")
            }

            withStyle(
                SpanStyle(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.primary,
                    fontStyle = FontStyle.Italic
                )
            ) {
                append("$${sliderValue.value.toInt()}")
            }
        },
        textAlign = TextAlign.Center
    )
}