package com.example.flight.presentation.screen.home.components.dialogUi.city

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight.R
import com.example.flight.presentation.screen.home.components.InputTextField
import com.example.flight.ui.theme.spacing
import com.example.flight.util.isTextValid
import com.example.flight.util.loadPopularCities

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DialogUiCity(
    text: MutableState<String>,
    keyboardController: SoftwareKeyboardController?,
    isDoneEnabled: MutableState<Boolean>,
    loading: Boolean,
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

                        if (!loading)
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
