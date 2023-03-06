package com.example.flight.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CheckIfArrivesNextDayTest {

    @Test
    fun `Util check if arrives next day, returns true`() {

        val dateStart = "2023-03-03"
        val dateEnd = "2023-03-04"

        assertThat(checkIfArrivesNextDay(dateStart, dateEnd)).isTrue()
    }

    @Test
    fun `Util check if arrives next day, returns false`() {

        val dateStart = "2023-03-03"
        val dateEnd = "2023-03-03"

        assertThat(checkIfArrivesNextDay(dateStart, dateEnd)).isFalse()
    }

}