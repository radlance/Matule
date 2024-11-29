package com.radlance.matule.presentation.common

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable

interface FetchResultUiState<T> {
    @Composable
    fun Show(onSuccess: @Composable (T) -> Unit, onError: @Composable (T?) -> Unit)
    data class Success<T>(private val data: T) : FetchResultUiState<T> {
        @Composable
        override fun Show(onSuccess: @Composable (T) -> Unit, onError: @Composable (T?) -> Unit) {
            onSuccess(data)
        }
    }

    class Error<T>(private val data: T?) : FetchResultUiState<T> {
        @Composable
        override fun Show(onSuccess: @Composable (T) -> Unit, onError: @Composable (T?) -> Unit) {
            onError(data)
        }
    }

    class Loading<T> : FetchResultUiState<T> {
        @Composable
        override fun Show(onSuccess: @Composable (T) -> Unit, onError: @Composable (T?) -> Unit) {
            CircularProgressIndicator()
        }
    }
}