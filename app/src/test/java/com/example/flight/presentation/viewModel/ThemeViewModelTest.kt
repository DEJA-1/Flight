package com.example.flight.presentation.viewModel

import com.example.flight.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ThemeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ThemeViewModel

    @Before
    fun setup() {
        viewModel = ThemeViewModel()
    }

    @Test
    fun `ThemeViewModel switch theme from light to dark`() {
        viewModel.isDarkTheme

        viewModel.switchTheme()

        assertThat(viewModel.isDarkTheme.value).isEqualTo(true)
    }

    @Test
    fun `ThemeViewModel switch theme from dark to light`() {
        viewModel.isDarkTheme.value = true

        viewModel.switchTheme()

        assertThat(viewModel.isDarkTheme.value).isEqualTo(false)
    }
}