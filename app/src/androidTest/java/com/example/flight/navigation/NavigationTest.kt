package com.example.flight.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.flight.MainActivity
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.FlightTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var themeViewModel: ThemeViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        themeViewModel = ThemeViewModel()

        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            FlightTheme(themeViewModel.isDarkTheme.value) {
                NavGraph(themeViewModel, navController)
            }
        }
    }

    @Test
    fun navigation_verifyStartDestination() {
        composeTestRule.onNodeWithTag("test_tag_home_screen").assertIsDisplayed()
    }

}