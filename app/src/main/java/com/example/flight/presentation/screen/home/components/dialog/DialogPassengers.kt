package com.example.flight.presentation.screen.home.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.R
import com.example.flight.presentation.screen.common_components.DoneButton
import com.example.flight.presentation.viewModel.HomeViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun DialogPassengers(
    openDialog: MutableState<Boolean>,
    onDoneQuitClick: () -> Unit
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUiPassengers(
                onDoneQuitClick
            )
        }
    }
}

@Composable
fun CustomDialogUiPassengers(
    onDoneQuitClick: () -> Unit
) {
    PassengersDialogUi(onDoneQuitClick)
}

@Composable
fun PassengersDialogUi(
    onDoneQuitClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val passengers = remember {
        mutableStateOf(1)
    }

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
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.large)
            ) {
                PassengerButton(
                    icon = painterResource(id = R.drawable.add)
                ) {
                    passengers.value++
                    viewModel.updatePassengers(passengers = passengers.value)
                }

                PassengerButton(
                    icon = painterResource(id = R.drawable.remove)
                ) {
                    if (passengers.value > 0)
                        passengers.value--
                    viewModel.updatePassengers(passengers = passengers.value)
                }
            }
            
        }
        
        DoneButton(isDoneEnabled = isDoneEnabled) {
            onDoneQuitClick()
        }
    }
}

@Composable
fun PassengerButton(
    icon: Painter,
    onPassengerButtonClicked: () -> Unit
) {
    Card(
        shape = CircleShape,
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                onPassengerButtonClicked()
            }
            .padding(MaterialTheme.spacing.extraSmall)
    ) {
        Icon(
            modifier = Modifier.padding(MaterialTheme.spacing.medium),
            painter = icon,
            contentDescription = "+/- icon",
            tint = Color.Black
        )
    }
}

