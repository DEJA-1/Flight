package com.example.flight.util
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CheckIfConnectionsTest {

    @Test
    fun `Util check if there are connections in selected itinerary, returns true`() {
        val connectionCount = 1

        assertThat(checkIfConnections(connectionCount)).isTrue()
    }

    @Test
    fun `Util check if there are connections in selected itinerary, returns false`() {
        val connectionCount = 0

        assertThat(checkIfConnections(connectionCount)).isFalse()
    }
}