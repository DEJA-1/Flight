package com.example.flight.presentation.screen.home.components.dialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flight.presentation.viewModel.HomeViewModel
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DialogDate(
    openDialog: MutableState<Boolean>,
    onCancelDateClicked: () -> Unit,
    onDatePicked: (String) -> Unit,
) {

    if (openDialog.value) {
        Dialog(onDismissRequest = { openDialog.value = false }) {
            CustomDialogUiDate(
                onCancelDateClicked = onCancelDateClicked,
                onDatePicked = onDatePicked
            )
        }
    }
}

@Composable
fun CustomDialogUiDate(
    onCancelDateClicked: () -> Unit,
    onDatePicked: (String) -> Unit,
) {
    DialogDatePicker(
        onCancelDateClicked = onCancelDateClicked,
        onDatePicked = onDatePicked
    )
}

@Composable
fun DialogDatePicker(
    onCancelDateClicked: () -> Unit,
    onDatePicked: (String) -> Unit,
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
            }
            negativeButton(text = "Cancel") {
                onCancelDateClicked()
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
