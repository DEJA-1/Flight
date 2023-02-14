package com.example.flight.presentation.screen.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flight.common.AppColors
import com.example.flight.presentation.viewmodel.ThemeViewModel
import com.example.flight.ui.theme.LocalSpacing
import com.example.flight.ui.theme.spacing

@Composable
fun Header(
    themeViewModel: ThemeViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = "FLIGHT",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colors.onBackground
        )
        CustomSwitch(isChecked = themeViewModel.isDarkTheme) {
            themeViewModel.switchTheme()
        }
    }

}

@Composable
fun CustomSwitch(
    isChecked: MutableState<Boolean>,
    onChange: () -> Unit
) {

    Surface(
        modifier = Modifier
            .size(70.dp, 40.dp)
            .padding(MaterialTheme.spacing.extraSmall),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colors.background,
        border = BorderStroke(2.dp, MaterialTheme.colors.onBackground),
        elevation = 4.dp,
    ) {
        Switch(
            checked = isChecked.value,
            onCheckedChange = { onChange() },
            colors = SwitchDefaults.colors(
                uncheckedTrackColor = MaterialTheme.colors.background,
                uncheckedThumbColor = Color.White
            )
        )

    }

}