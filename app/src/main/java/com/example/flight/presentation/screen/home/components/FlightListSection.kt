package com.example.flight.presentation.screen.home.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.example.flight.data.network.response.flight.Itinerary
import com.example.flight.ui.theme.spacing
import com.example.flight.util.compareDate
import com.example.flight.util.convertTimeToHours

@Composable
fun FlightListSection(
    modifier: Modifier = Modifier,
    flightList: List<Itinerary>, // later list<Flight>
    onFlightClick: () -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 4.dp)
    ) {
        items(flightList) { flight ->
            FlightRow(flight = flight, modifier = Modifier, onFlightClick = onFlightClick)
        }
    }

}

@Composable
fun FlightRow(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    flight: Itinerary?,
    onFlightClick: () -> Unit
) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .clickable {
                onFlightClick()
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
                        .data(flight?.slice_data?.slice_0?.airline?.logo)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "airline Image"
                )
            }

            Column() {

                Text(
                    modifier = Modifier.padding(top = 4.dp, end = 8.dp),
                    text = "${flight?.slice_data?.slice_0?.departure?.airport?.city} -> ${flight?.slice_data?.slice_0?.arrival?.airport?.city}",
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )


                Column {

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
                                text = "${convertTimeToHours(flight?.slice_data?.slice_0?.info?.duration.toString())}h",
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        if (compareDate(
                                flight?.slice_data?.slice_0?.departure?.datetime?.date.toString(),
                                flight?.slice_data?.slice_0?.arrival?.datetime?.date.toString()
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
                                        append("$${flight?.price_details?.baseline_total_fare_per_ticket?.toInt()}")
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

                        if (flight?.slice_data?.slice_0?.info?.connection_count != 0) {
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
                                    text = "${flight?.slice_data?.slice_0?.info?.connection_count} stop(s)",
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
                                modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 6.dp),
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
                                    text = flight?.slice_data?.slice_0?.airline?.name.toString(),
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
