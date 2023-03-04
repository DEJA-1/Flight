package com.example.flight.presentation.screen.info.components.dialog.stop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.flight.domain.model.flight.FlightModel
import com.example.flight.presentation.screen.common_components.DoneButton
import com.example.flight.presentation.screen.common_components.MyDivider
import com.example.flight.ui.theme.spacing
import com.example.flight.util.departureCityString


@Composable
fun DialogStop(
    openDialog: MutableState<Boolean>,
    itineraries: List<FlightModel>,
    onDoneQuitClick: () -> Unit,
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUiStop(flights = itineraries) {
                onDoneQuitClick()
            }
        }
    }
}

@Composable
fun CustomDialogUiStop(
    flights: List<FlightModel>,
    onDoneQuitClick: () -> Unit,
) {
    StopDialogUi(
        flights = flights
    ) {
        onDoneQuitClick()
    }
}

@Composable
fun StopDialogUi(flights: List<FlightModel>, onDoneQuitClick: () -> Unit) {

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

        LazyColumn() {
            items(flights.size) { index ->
                FlightInfoRow(index, flights = flights)
            }
        }

        DoneButton(isDoneEnabled = isDoneEnabled) {
            onDoneQuitClick()
        }
    }


}

@Composable
fun FlightInfoRow(
    index: Int = 1,
    flights: List<FlightModel>
) {

    Column() {

        Card(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            shape = RoundedCornerShape(10.dp),
            backgroundColor = MaterialTheme.colors.surface,
            elevation = 4.dp
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f)
                    .background(MaterialTheme.colors.surface)
                    .padding(
                        start = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Flight: ${index + 1}",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = MaterialTheme.spacing.extraSmall,
                            bottom = MaterialTheme.spacing.extraSmall,
                            start = MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.small
                        )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(

                        ) {
                            FlightInfo(
                                city = departureCityString(flights[index].departure.airport),
                                airport = flights[index].departure.airport.name.substringAfter("- "),
                                date = flights[index].departure.datetime.dateDisplay.substringBeforeLast(
                                    ","
                                ).substringAfter(", "),
                                time = flights[index].departure.datetime.time24h
                            )
                        }

                        Column(

                        ) {
                            FlightInfo(
                                city = departureCityString(flights[index].arrival.airport),
                                airport = flights[index].arrival.airport.name.substringAfter("- "),
                                date = flights[index].arrival.datetime.dateDisplay.substringBeforeLast(
                                    ","
                                ).substringAfter(", "),
                                time = flights[index].arrival.datetime.time24h
                            )
                        }

                    }

                }
                MyDivider()

            }
        }

    }
}

@Composable
fun FlightInfo(
    city: String,
    airport: String,
    date: String,
    time: String
) {
    Text(
        text = city,
        fontSize = 12.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Text(
        text = airport,
        fontSize = 12.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Text(
        text = date,
        fontSize = 12.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Text(
        text = time,
        fontSize = 12.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}