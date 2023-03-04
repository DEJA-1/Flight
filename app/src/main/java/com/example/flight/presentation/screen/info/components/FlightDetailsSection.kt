package com.example.flight.presentation.screen.info.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight.domain.model.FlightSearchParametersState
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.presentation.screen.common_components.MyDivider
import com.example.flight.presentation.viewModel.CommonViewModel
import com.example.flight.ui.theme.spacing
import com.example.flight.util.arrivalCityString
import com.example.flight.util.departureCityString

@Composable
fun FlightDetailsSection(
    itinerary: ItineraryModel,
    passengersCount: Int,
    onSaveClicked: () -> Unit
) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MaterialTheme.spacing.medium),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = departureCityString(itinerary.sliceData!!.slice.departure.airport),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )

                    MyDivider()

                    Text(
                        text = itinerary.sliceData.slice.departure.datetime.dateDisplay.substringBeforeLast(
                            ","
                        ),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )

                    MyDivider()

                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = departureCityString(itinerary.sliceData!!.slice.arrival.airport),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 22.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )

                    MyDivider()

                    Text(
                        text = itinerary.sliceData!!.slice.arrival.datetime.dateDisplay.substringBeforeLast(
                            ","
                        ),
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )

                    MyDivider()
                }
            }


            Text(
                text = itinerary.sliceData!!.slice.airline.name,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )



            MyDivider()

            Text(
                text = "$passengersCount passenger(s)",
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            MyDivider()

            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    ) {
                        append("price per ticket: ")
                    }

                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$${itinerary.priceDetails?.totalPerTicket}")
                    }
                }
            )

        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.small),
            contentAlignment = Alignment.BottomCenter
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.onBackground,
                                fontSize = 32.sp
                            )
                        ) {
                            append("Total: ")
                        }

                        withStyle(
                            SpanStyle(
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.ExtraBold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 32.sp
                            )
                        ) {
                            append("$${itinerary.priceDetails?.total}")
                        }
                    }
                )

                Button(onSaveClicked)
            }

        }

    }

}

@Composable
private fun Button(onSaveClicked: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .clickable {
                onSaveClicked()
            },
        color = MaterialTheme.colors.primary,
        elevation = 4.dp,
        shape = RoundedCornerShape(18.dp)
    ) {
        Text(
            modifier = Modifier.padding(MaterialTheme.spacing.small),
            fontWeight = FontWeight.Bold,
            text = "SAVE",
            textAlign = TextAlign.Center
        )
    }
}
