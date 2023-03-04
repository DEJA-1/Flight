package com.example.flight.util

import androidx.compose.runtime.MutableState

fun updateIsDialogOpen(isDialogOpen: MutableState<Boolean>) {
    isDialogOpen.value = !isDialogOpen.value
}