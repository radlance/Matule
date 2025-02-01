package com.radlance.matule.presentation.common

import android.location.Location
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.radlance.matule.ui.theme.inputFieldTextColor
import com.radlance.matule.ui.theme.poppinsFamily

interface LocationClientResultUiState {
    @Composable
    fun Show(onSuccess: @Composable (Location) -> Unit, onError: @Composable (String) -> Unit)

    data class Success(private val location: Location) : LocationClientResultUiState {
        @Composable
        override fun Show(
            onSuccess: @Composable (Location) -> Unit,
            onError: @Composable (String) -> Unit
        ) {
            onSuccess(location)
        }

    }

    data class Error(private val message: String) : LocationClientResultUiState {
        @Composable
        override fun Show(
            onSuccess: @Composable (Location) -> Unit,
            onError: @Composable (String) -> Unit
        ) {
            onError(message)
        }

    }

    data class Initial(private val message: String) : LocationClientResultUiState {
        @Composable
        override fun Show(
            onSuccess: @Composable (Location) -> Unit,
            onError: @Composable (String) -> Unit
        ) {
            Text(
                text = message,
                color = inputFieldTextColor,
                fontSize = 12.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Normal,
                lineHeight = 16.sp,
                modifier = Modifier.padding(start = 20.dp, end = 32.dp)
            )
        }
    }
}