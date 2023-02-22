package com.example.flight.presentation.screen.home.components.dialogUi.sort

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.flight.ui.theme.spacing

@Composable
fun SortRow(
    item: String,
    selectedSort: MutableState<String>,
    onSortClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth(0.6f)
            .padding(MaterialTheme.spacing.extraSmall)
            .clickable {
                onSortClicked(item)
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
