package com.radlance.matule.presentation.common

import androidx.compose.runtime.Composable

interface FetchResultUiState<T> {
    @Composable
    fun Show(
        onSuccess: @Composable (T) -> Unit,
        onError: @Composable (T?) -> Unit,
        onLoading: @Composable (T?) -> Unit,
        onUnAuthorized: (@Composable () -> Unit)
    )
    data class Success<T>(val data: T) : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit,
            onUnAuthorized: @Composable () -> Unit
        ) {
            onSuccess(data)
        }
    }

    class Error<T>(private val data: T?) : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit,
            onUnAuthorized: @Composable () -> Unit
        ) {
            onError(data)
        }
    }

    class Unauthorized<T> : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit,
            onUnAuthorized: @Composable () -> Unit
        ) {
            onUnAuthorized()
        }
    }

    class Loading<T>(private val data: T? = null) : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit,
            onUnAuthorized: @Composable () -> Unit
        ) {
            onLoading(data)
        }
    }

    class Initial<T> : FetchResultUiState<T> {
        @Composable
        override fun Show(
            onSuccess: @Composable (T) -> Unit,
            onError: @Composable (T?) -> Unit,
            onLoading: @Composable (T?) -> Unit,
            onUnAuthorized: @Composable () -> Unit
        ) {
            onUnAuthorized()
        }
    }
}