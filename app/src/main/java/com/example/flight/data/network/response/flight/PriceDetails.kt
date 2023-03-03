package com.example.flight.data.network.response.flight

import com.example.flight.domain.model.flight.PriceDetailsModel

data class PriceDetails(
    val accepted_credit_cards: List<Any>,
    val baseline_baseFare: Double,
    val baseline_currency: String,
    val baseline_fees: Double,
    val baseline_pcln_fees: Double,
    val baseline_ppn_fees: Double,
    val baseline_symbol: String,
    val baseline_taxes: Double,
    val baseline_taxes_and_fees: Double,
    val baseline_taxes_and_ppn_fees: Double,
    val baseline_total_fare: Double,
    val baseline_total_fare_per_ticket: Double,
    val component_information_list: List<Any>,
    val display_base_fare: Double,
    val display_currency: String,
    val display_fees: Double,
    val display_pcln_fees: Double,
    val display_ppn_fees: Double,
    val display_symbol: String,
    val display_taxes: Double,
    val display_taxes_and_fees: Double,
    val display_taxes_and_ppn_fees: Double,
    val display_total_fare: Double,
    val display_total_fare_per_ticket: Double,
    val savings: List<Any>,
    val source_base_fare: Double,
    val source_currency: String,
    val source_fees: Double,
    val source_pcln_fees: Double,
    val source_ppn_fees: Double,
    val source_taxes: Double,
    val source_taxes_and_fees: Double,
    val source_taxes_and_ppn_fees: Double,
    val source_total_fare: Double,
    val source_total_fare_per_ticket: Double
)

fun PriceDetails.toPriceDetailsModel(): PriceDetailsModel =
    PriceDetailsModel(
        totalPerTicket = display_total_fare_per_ticket,
        total = display_total_fare
    )