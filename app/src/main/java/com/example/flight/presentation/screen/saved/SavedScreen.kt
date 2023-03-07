package com.example.flight.presentation.screen.saved

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.flight.navigation.Screen
import com.example.flight.presentation.screen.common_components.Header
import com.example.flight.presentation.screen.common_components.MyDivider
import com.example.flight.presentation.screen.home.components.FlightListSection
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.presentation.viewModel.SavedViewModel
import com.example.flight.ui.theme.spacing

@Composable
fun SavedScreen(
    navigateToInfoScreen: () -> Unit,
    commonViewModel: CommonViewModel,
    viewModel: SavedViewModel
) {

    val itinerariesFromDatabase by viewModel.itinerariesFromDb.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(
                isSwitchThemeButtonVisible = false,
            )

            MyDivider()

            Text(
                text = "Your flights",
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center
            )

            when (itinerariesFromDatabase.isEmpty()) {
                true -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No flights saved",
                            color = MaterialTheme.colors.secondary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                else -> {
                    FlightListSection(
                        itineraries = itinerariesFromDatabase,
                        isSaved = true,
                        onDeleteClick = { itinerary ->
                            viewModel.deleteItineraryFromDb(itinerary)
                            Toast.makeText(context, "Itinerary deleted successfully", Toast.LENGTH_SHORT).show()
                        }
                    ) { itinerary ->
                        commonViewModel.updateCurrentItinerary(itinerary)
                        navigateToInfoScreen()
                    }
                }
            }


        }

    }

}
