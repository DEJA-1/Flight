package com.example.flight.presentation.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ParamsSection(
    modifier: Modifier = Modifier,
    buttonNames: List<String>,
    isFilter: Boolean,
    selectedButtonIndex: Int,
    onButtonClick: (Int, String) -> Unit,
) {
    LazyRow() {
        items(buttonNames.size) { index ->
            Box(
                modifier = modifier
                    .padding(top = 10.dp, end = 8.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .clickable {
                        onButtonClick(index, buttonNames[index])
//                            if (buttonList[index] == "Sort by")
//                                isExpanded.value = !isExpanded.value
                    }
                    .border(
                        width = 2.dp, shape = RoundedCornerShape(18.dp),
                        color = if (selectedButtonIndex == index && !isFilter)
                            MaterialTheme.colors.primary
                        else
                            if (buttonNames[index] == "SAVE")
                                MaterialTheme.colors.primary
                            else
                                MaterialTheme.colors.secondary
                    )
                    .background(
                        if (!isFilter)
                            MaterialTheme.colors.surface
                        else
                            if (buttonNames[index] == "SAVE")
                                MaterialTheme.colors.primary
                            else
                                MaterialTheme.colors.background

                    )
                    .padding(10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buttonNames[index],
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold
                )
            }

        }

    }
}

