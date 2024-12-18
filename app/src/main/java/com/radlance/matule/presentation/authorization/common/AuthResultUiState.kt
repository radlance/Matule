package com.radlance.matule.presentation.authorization.common

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

interface AuthResultUiState {
    @Composable
    fun Show(
        onSuccessResult: () -> Unit,
        onChangeButtonState: (enabled: Boolean) -> Unit,
        snackBarHostState: SnackbarHostState
    )

    object Initial : AuthResultUiState {
        @Composable
        override fun Show(
            onSuccessResult: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackBarHostState: SnackbarHostState
        ) {}
    }

    object Success : AuthResultUiState {
        @Composable
        override fun Show(
            onSuccessResult: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackBarHostState: SnackbarHostState
        ) {
            LaunchedEffect(Unit) {
                onSuccessResult()
            }
        }
    }

    data class Error(private val message: String) : AuthResultUiState {
        @Composable
        override fun Show(
            onSuccessResult: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackBarHostState: SnackbarHostState
        ) {
            onChangeButtonState(true)
            LaunchedEffect(message) {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Indefinite,
                    withDismissAction = true
                )
            }
        }
    }

    data class Loading(private val message: String) : AuthResultUiState {
        @Composable
        override fun Show(
            onSuccessResult: () -> Unit,
            onChangeButtonState: (Boolean) -> Unit,
            snackBarHostState: SnackbarHostState
        ) {
            onChangeButtonState(false)
            LaunchedEffect(message) {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }
    }
}