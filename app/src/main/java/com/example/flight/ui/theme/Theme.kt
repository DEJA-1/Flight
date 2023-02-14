package com.example.flight.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.flight.common.AppColors
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = AppColors.mMain,
    primaryVariant = AppColors.mYellow,
    secondary = AppColors.mDarkGray,
    background = AppColors.mBackgroundDark,
    surface = AppColors.mBackgroundSecDark,
    onBackground = AppColors.mTextWhite
)

private val LightColorPalette = lightColors(
    primary = AppColors.mMainLight,
    primaryVariant = AppColors.mOrange,
    secondary = AppColors.mLightGray,
    background = AppColors.mBackgroundLight,
    surface = AppColors.mBackgroundSecLight,
    onBackground = AppColors.mTextBlack

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun FlightTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if (darkTheme) {
        systemUiController.setSystemBarsColor(AppColors.mBackgroundDark)
    } else {
        systemUiController.setSystemBarsColor(AppColors.mBackgroundLight)
    }

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}