@file:OptIn(ExperimentalComposeUiApi::class)

package com.example.flight.presentation.screen.home.components.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.flight.R
import com.example.flight.presentation.screen.common_components.DoneButton
import com.example.flight.presentation.screen.home.components.InputTextField
import com.example.flight.ui.theme.spacing
import com.example.flight.util.isTextValid
import com.example.flight.util.loadPopularCities
import java.util.*

@Composable
fun DialogCity(
    openDialog: MutableState<Boolean>,
    loadingLocation: Boolean,
    onDoneQuitClick: () -> Unit,
    onDoneClick: (String) -> Unit,
) {

    val text = remember {
        mutableStateOf("")
    }

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUiCity(
                text = text,
                loadingLocation = loadingLocation,
                onDoneQuitClick = onDoneQuitClick
            ) { city ->
                onDoneClick(city)
            }
        }
    }
}

@Composable
fun CustomDialogUiCity(
    text: MutableState<String>,
    loadingLocation: Boolean,
    onDoneQuitClick: () -> Unit,
    onDoneClick: (String) -> Unit,
) {
    CityDialogUi(
        text = text,
        loadingLocation = loadingLocation,
        onDoneClick = onDoneClick,
        onDoneQuitClick = onDoneQuitClick
    )
}

@Composable
fun CityDialogUi(
    text: MutableState<String>,
    loadingLocation: Boolean,
    onDoneClick: (String) -> Unit,
    onDoneQuitClick: () -> Unit,
) {

    val isDoneEnabled = remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

        DoneButton(
            isDoneEnabled = isDoneEnabled,
            loadingLocation = loadingLocation,
            onDoneQuitClick = onDoneQuitClick
        )
    }
}






