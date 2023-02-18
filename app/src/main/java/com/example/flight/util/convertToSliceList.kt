package com.example.flight.util

import com.example.flight.domain.model.flight.Slice
import com.example.flight.domain.model.flight.SliceData

fun convertToSliceList(sliceData: SliceData) =
    listOf(
        sliceData.slice0,
        sliceData.slice1,
        sliceData.slice2,
        sliceData.slice3,
        sliceData.slice4,
        sliceData.slice5,
        sliceData.slice6,
        sliceData.slice7,
        sliceData.slice8,
        sliceData.slice9,
    )