package com.example.flight.presentation.screen.home.components.dialogUi.stop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flight.domain.model.flight.FlightModel
import com.example.flight.presentation.screen.info.components.FlightDrawing
import com.example.flight.ui.theme.spacing


@Composable
fun DialogUiStopInfo(isDoneEnabled: MutableState<Boolean>, flights: List<FlightModel>) {

    Box(
        modifier = Modifier.fillMaxSize(0.8f),
        contentAlignment = Alignment.Center
    ) {

        LazyColumn() {
            items(flights.size) { index ->
                FlightInfoRow(index)
            }
        }

    }

}

@Preview
@Composable
fun FlightInfoRow(
    index: Int = 1,
    flights: List<String> = listOf("aaa", "bbb", "ccc")
) {

    Row(
        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.2f)
            .width(400.dp)
            .height(80.dp)
            .background(MaterialTheme.colors.surface)
            .padding(start = MaterialTheme.spacing.small, end = MaterialTheme.spacing.small),
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
                .padding(start = MaterialTheme.spacing.small, end = MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(
                        text = "Warsaw"
                    )

                    Text(
                        text = "Chopin airport"
                    )

                    Text(
                        text = "24th February"
                    )

                    Text(
                        text = "11:53"
                    )
                }

                FlightDrawing()

                Column() {
                    Text(
                        text = "Paris"
                    )

                    Text(
                        text = "CDG airport"
                    )

                    Text(
                        text = "24th February"
                    )

                    Text(
                        text = "13:53"
                    )
                }

            }
        }


        //MyDivider()
        Divider(
            thickness = 2.dp,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.small
            )
        )

    }


}