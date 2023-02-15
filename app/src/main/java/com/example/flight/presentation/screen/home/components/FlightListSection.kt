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
import com.example.flight.ui.theme.spacing

@Composable
fun FlightListSection(
    modifier: Modifier = Modifier,
    flightList: List<String>, // later list<Flight>
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
    flight: String, // later flight: Fight
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
                    model = ImageRequest.Builder(context)
                        .data("https://lofrev.net/wp-content/photos/2017/05/ryanair_logo.jpg")
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "airline Image"
                )
            }

            Column() {
                Text(
                    modifier = Modifier.padding(top = 4.dp, end = 16.dp),
                    text = "Wroclaw -> Paris",
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
                                text = "5h",
                                color = MaterialTheme.colors.primaryVariant,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

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
                                text = "1 stop",
                                color = Color(0xFF3f48cc),
                                fontWeight = FontWeight.ExtraBold,
                                fontStyle = FontStyle.Italic,
                                fontSize = 15.sp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                    }


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
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
                                        append("$700")
                                    }

                                    withStyle(
                                        SpanStyle(
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    ) {
                                        append(" - ")
                                    }

                                    withStyle(
                                        SpanStyle(
                                            fontWeight = FontWeight.Bold,
                                            color = AppColors.mRed,
                                            fontStyle = FontStyle.Italic
                                        )
                                    ) {
                                        append("$1400")
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


                        Surface(
                            modifier = Modifier.padding(start = 4.dp, top = 4.dp, end = 4.dp),
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
                                text = "RYANAIR",
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