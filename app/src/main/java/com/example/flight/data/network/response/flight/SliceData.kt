package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.SlicesModel

data class SliceData(
    val slice_0: Slice
)

fun SliceData.toSliceDataModel(): SlicesModel =
    SlicesModel(
        slice = slice_0.toSliceModel()
    )