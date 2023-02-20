package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.SliceDataModel
import com.google.gson.annotations.SerializedName

data class SliceData(
    val slice_0: Slice
)

fun SliceData.toSliceDataModel(): SliceDataModel =
    SliceDataModel(
        slice = slice_0.toSliceModel()
    )