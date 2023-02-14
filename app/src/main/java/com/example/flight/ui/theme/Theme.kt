package com.example.flight.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.flight.common.AppColors

private val DarkColorPalette = darkColors(
    primary = AppColors.mMain,
    primaryVariant = Purple700,
    secondary = AppColors.mDarkGray,
    background = AppColors.mBackgroundDark,
    surface = AppColors.mBackgroundSecDark,
    onBackground = AppColors.mTextWhite
)

private val LightColorPalette = lightColors(
    primary = AppColors.mMain,
    primaryVariant = Purple700,
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
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
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