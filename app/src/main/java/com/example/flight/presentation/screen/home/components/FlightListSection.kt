package com.example.flight.presentation.screen.home.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.flight.common.AppColors
import com.example.flight.common.Constants
import com.example.flight.domain.model.flight.ItineraryModel
import com.example.flight.ui.theme.spacing
import com.example.flight.util.checkIfArrivesNextDay
import com.example.flight.util.convertTimeToHours
import com.example.flight.util.setCityName

@Composable
fun FlightListSection(
    modifier: Modifier = Modifier,
    itineraries: List<ItineraryModel>,
    isSaved: Boolean,
    onDeleteClick: (ItineraryModel) -> Unit = {},
    onFlightClick: (ItineraryModel) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        items(itineraries) { flight ->
            FlightRow(
                modifier = Modifier,
                itinerary = flight,
                isSaved = isSaved,
                onDeleteClick = onDeleteClick,
                onFlightClick = onFlightClick
            )
        }
    }

}

@Composable
fun FlightRow(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    itinerary: ItineraryModel,
    isSaved: Boolean,
    onDeleteClick: (ItineraryModel) -> Unit,
    onFlightClick: (ItineraryModel) -> Unit
) {


    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onFlightClick(itinerary)
            },
        shape = RoundedCornerShape(6.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {

        Row(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight()
                    .padding(MaterialTheme.spacing.small)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colors.background)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context)
                        .data(itinerary.sliceData!!.slice.airline.logo)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "airline Image"
                )

                if (isSaved) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = AppColors.mRed,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.extraSmall)
                            .clickable {
                                onDeleteClick(itinerary)
                            }
                            .align(Alignment.BottomStart),
                    ) {
                        Icon(
                            modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                            imageVector = Icons.Filled.Delete, contentDescription = "Delete icon",
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                }
            }

            Column() {
                Text(
                    modifier = Modifier.padding(
                        top = MaterialTheme.spacing.extraSmall,
                        end = MaterialTheme.spacing.medium
                    ),
                    text = "${setCityName(itinerary.sliceData!!.slice.departure.airport)} -> ${setCityName(itinerary.sliceData.slice.arrival.airport)}",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {

                        Row() {
                            Surface(
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colors.background,
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        top = 2.dp,
                                        bottom = 2.dp,
                                        start = 6.dp,
                                        end = 6.dp
                                    ),
                                    text = "${convertTimeToHours(itinerary.sliceData!!.slice.info.duration)}h",
                                    color = MaterialTheme.colors.primaryVariant,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 15.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }

                            if (checkIfArrivesNextDay(
                                    itinerary.sliceData!!.slice.departure.datetime.date,
                                    itinerary.sliceData.slice.arrival.datetime.date
                                )
                            ) {
                                Surface(
                                    modifier = Modifier.padding(start = 2.dp),
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colors.background
                                ) {
                                    Text(
                                        modifier = Modifier.padding(
                                            top = 2.dp,
                                            bottom = 2.dp,
                                            start = 6.dp,
                                            end = 6.dp
                                        ),
                                        text = "Arrives next day",
                                        color = AppColors.mRed,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontStyle = FontStyle.Italic,
                                        fontSize = 15.sp,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                }
                            }
                        }
                        Surface(
                            modifier = Modifier.padding(
                                start = MaterialTheme.spacing.extraSmall,
                                end = MaterialTheme.spacing.extraSmall
                            ),
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colors.background,
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    top = 2.dp,
                                    bottom = 2.dp,
                                    start = 6.dp,
                                    end = 6.dp
                                ),
                                text = itinerary.sliceData!!.slice.departure.datetime.time24h,
                                color = MaterialTheme.colors.onBackground,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 15.sp,
                                maxLines = 1
                            )
                        }

                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Surface(
                            modifier = Modifier.padding(top = 4.dp),
                            shape = RoundedCornerShape(16.dp),
                            color = MaterialTheme.colors.background,
                        ) {
                            Text(
                                buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colors.primary,
                                            fontStyle = FontStyle.Italic
                                        )
                                    ) {
                                        append("$${itinerary.priceDetails!!.totalPerTicket.toInt()}")
                                    }

                                },
                                modifier = Modifier.padding(
                                    top = 2.dp,
                                    bottom = 2.dp,
                                    start = 6.dp,
                                    end = 6.dp
                                ),
                            )
                        }

                        if (itinerary.sliceData!!.slice.info.connectionCount != 0) {
                            Surface(
                                modifier = Modifier.padding(start = 2.dp, top = 4.dp),
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colors.background
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        top = 2.dp,
                                        bottom = 2.dp,
                                        start = 6.dp,
                                        end = 6.dp
                                    ),
                                    text = "${itinerary.sliceData.slice.info.connectionCount} stop(s)",
                                    color = AppColors.mBlue,
                                    fontWeight = FontWeight.ExtraBold,
                                    fontStyle = FontStyle.Italic,
                                    fontSize = 15.sp,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomEnd
                        ) {
                            Surface(
                                modifier = Modifier.padding(
                                    start = 4.dp,
                                    top = 4.dp,
                                    end = 4.dp,
                                    bottom = 6.dp
                                ),
                                shape = RoundedCornerShape(16.dp),
                                color = MaterialTheme.colors.background,
                            ) {

                                Text(
                                    modifier = Modifier.padding(
                                        top = 2.dp,
                                        bottom = 2.dp,
                                        start = 6.dp,
                                        end = 6.dp
                                    ),
                                    text = itinerary.sliceData.slice.airline.name,
                                    maxLines = 1,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.onBackground,
                                    overflow = TextOverflow.Ellipsis
                                )

                            }

                        }

                    }

                }

            }
        }

    }
}



