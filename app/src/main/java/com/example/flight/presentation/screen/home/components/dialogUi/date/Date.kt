package com.example.flight.presentation.screen.home.components.dialogUi.date

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DialogDatePicker(
    viewModel: HomeViewModel = hiltViewModel(),
    onDatePicked: (String) -> Unit
) {
    val dateDialogState = rememberMaterialDialogState()
    val pickedDate = remember {
        mutableStateOf(LocalDate.now())
    }

    val formattedDate = remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .format(pickedDate.value)
        }
    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnClickOutside = true
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        buttons = {
            positiveButton(text = "Ok") {
                onDatePicked(formattedDate.value)
                viewModel.updateIsDialogOpen()
            }
            negativeButton(text = "Cancel") {
                viewModel.updateIsDialogOpen()
            }
        }
    ) {
        this.datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a date",
            allowedDateValidator = {
                it > LocalDate.now()
            }
        ) { date ->
            pickedDate.value = date
        }
    }

    dateDialogState.show()

}
