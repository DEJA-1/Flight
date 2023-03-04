package com.example.flight.presentation.screen.info

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flight.presentation.screen.info.components.FlightDetailsSection
import com.example.flight.presentation.screen.info.components.FlightInfoSection
import com.example.flight.presentation.screen.info.components.dialog.stop.DialogStop
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.InfoViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun InfoScreen(
    viewModel: InfoViewModel,
    commonViewModel: CommonViewModel
) {

    val itinerary = commonViewModel.currentItinerary.value
    val currentFlightSearchParameters by commonViewModel.currentFlightParams.collectAsState()

    val isDialogOpen = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://images.pexels.com/photos/912050/pexels-photo-912050.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                        .crossfade(true)
                        .build(),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small)) {

                Divider(
                    thickness = 2.dp,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.medium,
                        bottom = MaterialTheme.spacing.medium
                    )
                )

                FlightInfoSection(
                    flight = itinerary
                ) {
                    isDialogOpen.value = !isDialogOpen.value
                }

                FlightDetailsSection(
                    itinerary = itinerary,
                    passengersCount = currentFlightSearchParameters.passengers,
                ) {
                    Toast.makeText(context, "Itinerary saved successfully", Toast.LENGTH_SHORT).show()
                    viewModel.addItineraryToDb(commonViewModel.currentItinerary.value)
                }

            }


        }

        if (isDialogOpen.value){
            DialogStop(openDialog = isDialogOpen, itineraries = itinerary.sliceData!!.slice.flightData.flights) {
                isDialogOpen.value = false
            }
        }

    }

}