package com.example.flight.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class IsTextValidTest {

    @Test
    fun `Util check if text is valid, returns false`() {
        val text = ""

        assertThat(isTextValid(text)).isFalse()
    }

    @Test
    fun `Util check if text is valid, returns true`() {
        val text = "text"

        assertThat(isTextValid(text)).isTrue()
    }
}