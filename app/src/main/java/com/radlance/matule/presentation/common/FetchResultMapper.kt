package com.radlance.matule.presentation.common

import com.radlance.matule.domain.remote.FetchResult

class FetchResultMapper<T> : FetchResult.Mapper<FetchResultUiState<T>, T> {
    override fun mapError(data: T?): FetchResultUiState<T> {
        return FetchResultUiState.Error(data)
    }

    override fun mapSuccess(data: T): FetchResultUiState<T> {
        return FetchResultUiState.Success(data)
    }
}