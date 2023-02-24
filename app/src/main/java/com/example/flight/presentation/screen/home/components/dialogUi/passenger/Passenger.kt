//package com.example.flight.presentation.screen.home.components.dialogUi.passenger
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.Card
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.flight.R
//import com.example.flight.presentation.viewModel.HomeViewModel
//import com.example.flight.ui.theme.spacing
//
//@Composable
//fun DialogUiPassenger(
//    isDoneEnabled: MutableState<Boolean>,
//    viewModel: HomeViewModel = hiltViewModel()
//) {
//    val passengers = remember {
//        mutableStateOf(1)
//    }
//    isDoneEnabled.value = true
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.8f)
//            .padding(MaterialTheme.spacing.small),
//        contentAlignment = Alignment.Center,
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//
//            Text(
//                text = passengers.value.toString(),
//                fontSize = 64.sp,
//                fontWeight = FontWeight.ExtraBold,
//                fontStyle = FontStyle.Italic,
//                color = MaterialTheme.colors.onBackground
//            )
//
//            Row{
//                PassengerButton(
//                    icon = painterResource(id = R.drawable.add)
//                ) {
//                    passengers.value++
//                    viewModel.updatePassengers(passengers = passengers.value)
//                }
//
//                PassengerButton(
//                    icon = painterResource(id = R.drawable.remove)
//                ) {
//                    if (passengers.value > 0)
//                        passengers.value--
//                    viewModel.updatePassengers(passengers = passengers.value)
//                }
//            }
//        }
//    }
//}
//
