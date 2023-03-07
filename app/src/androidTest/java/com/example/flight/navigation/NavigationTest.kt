package com.example.flight.navigation

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.filters.MediumTest
import com.example.flight.MainActivity
import com.example.flight.common.Constants
import com.example.flight.presentation.viewModel.ThemeViewModel
import com.example.flight.ui.theme.FlightTheme
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify

@MediumTest
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
        composeTestRule.onNodeWithTag(Constants.TEST_TAG_HOME_SCREEN).assertIsDisplayed()
    }
    @Test
    fun navigation_navigateToSavedScreen() {
        composeTestRule.onNodeWithTag(Constants.TEST_TAG_FAB).performClick()
        val route = navController.currentDestination?.route
        assertThat(route).isEqualTo(Screen.Saved.route)
    }

    @Test
    fun navigation_popBackStack() {
        composeTestRule.onNodeWithTag(Constants.TEST_TAG_FAB).performClick()
        val route = navController.currentDestination?.route
        assertThat(route).isEqualTo(Screen.Saved.route)

        Espresso.pressBack()

        assertThat(navController.currentDestination?.route).isEqualTo(Screen.Home.route)
    }
}