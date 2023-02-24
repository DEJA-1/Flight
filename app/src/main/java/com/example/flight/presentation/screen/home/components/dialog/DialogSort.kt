package com.example.flight.presentation.screen.home.components.dialog

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.flight.presentation.screen.common_components.DoneButton
import com.example.flight.presentation.screen.home.components.dialogUi.sort.SortRow
import com.example.flight.ui.theme.spacing

@Composable
fun DialogSort(
    openDialog: MutableState<Boolean>,
    selectedSort: MutableState<String>,
    onDoneQuitClick: () -> Unit,
    onSortClick: (String) -> Unit
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUiSort(
                selectedSort = selectedSort,
                onDoneQuitClick = onDoneQuitClick
            ) { city ->
                onSortClick(city)
            }
        }
    }
}

@Composable
fun CustomDialogUiSort(
    selectedSort: MutableState<String>,
    onDoneQuitClick: () -> Unit,
    onSortClick: (String) -> Unit
) {
    SortDialogUi(
        selectedSort = selectedSort,
        onSortClick = onSortClick,
        onDoneQuitClick = onDoneQuitClick
    )
}

@Composable
fun SortDialogUi(
    selectedSort: MutableState<String>,
    onSortClick: (String) -> Unit,
    onDoneQuitClick: () -> Unit
) {

    val isDoneEnabled = remember {
        mutableStateOf(true)
    }

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxHeight(0.7f)
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn() {
                items(listOf("Price", "Departure time", "Duration")) { item ->
                    MySortRow(item = item, selectedSort = selectedSort) {
                        onSortClick(item)
                    }
                }
            }


        }

        DoneButton(isDoneEnabled = isDoneEnabled) {
            onDoneQuitClick()
        }

    }

}


@Composable
fun MySortRow(
    item: String,
    selectedSort: MutableState<String>,
    onSortClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.extraSmall)
            .clickable {
                onSortClick()
            },
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colors.surface,
        border = BorderStroke(
            width = 2.dp, color = if (selectedSort.value == item)
                MaterialTheme.colors.primary
            else
                MaterialTheme.colors.secondary
        )
    ) {
        Text(
            modifier = Modifier.padding(
                top = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.small
            ),
            text = item,
            color = MaterialTheme.colors.onBackground,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}