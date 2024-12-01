package com.radlance.matule.presentation.common

import androidx.compose.runtime.Composable

interface FetchResultUiState<T> {
    @Composable
    fun Show(
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (T?) -> Unit,
        onLoading: @Composable (T?) -> Unit,
    )
    data class Success<T>(val data: T) : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit
        ) {
            onSuccess(data)
        }
    }

    class Error<T>(private val data: T?) : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit
        ) {
            onError(data)
        }
    }

    class Loading<T>(private val data: T? = null) : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit
        ) {
            onLoading(data)
        }
    }

    class Initial<T> : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit
        ) {}
    }
}