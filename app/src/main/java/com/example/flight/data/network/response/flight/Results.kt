package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.ResultsModel

data class Results(
    val result: Result,
    val status: String,
    val status_code: Int,
    val time: String
)

fun Results.toResultsModel() =
    ResultsModel(
        result = result.toResultModel()
    )