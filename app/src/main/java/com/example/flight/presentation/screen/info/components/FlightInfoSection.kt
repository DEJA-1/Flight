package com.example.flight.presentation.screen.info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.flight.common.AppColors
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.ui.theme.spacing
import com.example.flight.util.checkIfConnections

@Composable
fun FlightInfoSection(
    flight: ItineraryModel,
    onStopInfoClicked: () -> Unit
) {

    val itineraryConnectionCount = flight.sliceData!!.slice.info.connectionCount

    Surface(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.2f)
                    .background(MaterialTheme.colors.surface),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = MaterialTheme.spacing.medium,
                            end = MaterialTheme.spacing.medium
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    MainInfo(
                        place = flight.sliceData!!.slice.departure.airport.code,
                        time = flight.sliceData.slice.departure.datetime.time24h
                    )

                    FlightDrawing()

                    MainInfo(
                        place = flight.sliceData.slice.arrival.airport.code,
                        time = flight.sliceData.slice.arrival.datetime.time24h
                    )
                }

                if (
                    checkIfConnections(itineraryConnectionCount)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Surface(
                            modifier = Modifier.size(10.dp),
                            color = AppColors.mBlue,
                            shape = CircleShape
                        ) {

                        }
                    }
                }

            }

            StopButton(itineraryConnectionCount) {
                onStopInfoClicked()
            }

        }

    }

}

@Composable
fun StopButton(
    itineraryConnectionCount: Int,
    onStopInfoClicked: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .fillMaxHeight(0.1f)
            .fillMaxWidth()
            .background(
                if (checkIfConnections(itineraryConnectionCount))
                    AppColors.mBlue
                else
                    MaterialTheme.colors.secondary
            )
            .clickable(
                enabled = checkIfConnections(itineraryConnectionCount)
            ) {
                onStopInfoClicked("Stop info")
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            text = "Stop info",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )

    }
}



