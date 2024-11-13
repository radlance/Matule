package com.radlance.matule.presentation.signup

import android.util.Log
import androidx.compose.runtime.Composable

interface SignUpResultUiState {
    @Composable
    fun Show()

    object Initial : SignUpResultUiState {
        @Composable
        override fun Show() {
            Log.d("SignUpResultUiState", "Initial")
        }
    }

    object Success : SignUpResultUiState {
        @Composable
        override fun Show() {
            Log.d("SignUpResultUiState", "Success")
        }
    }

    object Error : SignUpResultUiState {
        @Composable
        override fun Show() {
            Log.d("SignUpResultUiState", "Error")
        }
    }

    object Loading : SignUpResultUiState {
        @Composable
        override fun Show() {
            Log.d("SignUpResultUiState", "Loading")
        }
    }
}