package com.example.flight.util

import com.example.flight.domain.model.flight.AirportModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SetCityNameTest {

    @Test
    fun `Util set city name sets city name`() {

        val exampleAirport = AirportModel(
            city = "Wroclaw",
            code = "WRO",
            country = "Poland",
            name = "Chopin airport"
        )

        assertThat(setCityName(exampleAirport)).isEqualTo("Wroclaw")
    }

    @Test
    fun `Util set city name sets city code`() {

        val exampleAirport = AirportModel(
            city = "",
            code = "WRO",
            country = "Poland",
            name = "Chopin airport"
        )

        assertThat(setCityName(exampleAirport)).isEqualTo("WRO")
    }

}