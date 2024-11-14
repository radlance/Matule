package com.radlance.matule.presentation.signup

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.radlance.matule.R

interface SignUpResultUiState {
    @Composable
    fun Show(
        onSuccessResult: () -> Unit,
        snackBarHostState: SnackbarHostState
    )

    object Initial : SignUpResultUiState {
        @Composable
        override fun Show(onSuccessResult: () -> Unit, snackBarHostState: SnackbarHostState) {
        }
    }

    object Success : SignUpResultUiState {
        @Composable
        override fun Show(onSuccessResult: () -> Unit, snackBarHostState: SnackbarHostState) {
            LaunchedEffect(Unit) {
                onSuccessResult()
            }
        }
    }

    data class Error(private val message: String) : SignUpResultUiState {
        @Composable
        override fun Show(onSuccessResult: () -> Unit, snackBarHostState: SnackbarHostState) {
            LaunchedEffect(message) {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Indefinite,
                    withDismissAction = true
                )
            }
        }
    }

    object Loading : SignUpResultUiState {
        @Composable
        override fun Show(onSuccessResult: () -> Unit, snackBarHostState: SnackbarHostState) {
            val message = stringResource(R.string.loading)
            LaunchedEffect(message) {
                snackBarHostState.showSnackbar(message = message)
            }
        }
    }
}