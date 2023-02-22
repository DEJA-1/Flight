package com.example.flight.presentation.screen.home.components.dialogUi.filter

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.flight.presentation.viewModel.HomeViewModel

@Composable
fun SliderSection(
    viewModel: HomeViewModel
) {

    val sliderValue = remember {
        mutableStateOf(viewModel.filterParams.value.maxPrice.value.toFloat())
    }

    Slider(
        modifier = Modifier.fillMaxWidth(),
        value = sliderValue.value,
        onValueChange = { newValue ->
            sliderValue.value = newValue
            viewModel.updateSliderValue(sliderValue.value)
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