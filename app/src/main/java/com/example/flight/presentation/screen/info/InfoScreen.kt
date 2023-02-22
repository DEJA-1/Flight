package com.example.flight.presentation.screen.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flight.common.AppColors
import com.example.flight.presentation.screen.home.components.Header
import com.example.flight.presentation.screen.info.components.FlightDetailsSection
import com.example.flight.presentation.screen.info.components.FlightInfoSection
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun InfoScreen(
    navController: NavController,
    themeViewModel: ThemeViewModel,
    commonViewModel: CommonViewModel
) {

    val flight = commonViewModel.currentItinerary.value

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
                    flight = flight
                ) {

                }

                FlightDetailsSection(
                    flight = flight
                ) {

                }

            }


        }

    }

}