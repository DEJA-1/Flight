package com.example.flight.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ConvertTimeToHoursTest {

    @Test
    fun `Util convert time to hours`() {
        // time "dd:HH:mm", for example "01:12:30" = 24h + 12h + 1/2h = 36,5h.toInt() = 36h
        val time = "01:12:30"
        val timeConvertedToHours = convertTimeToHours(time)

        assertThat(timeConvertedToHours).isEqualTo(36)
    }

}