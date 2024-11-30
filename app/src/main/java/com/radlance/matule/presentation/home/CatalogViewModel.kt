package com.radlance.matule.presentation.home

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.home.CatalogFetchContent
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.remote.FetchResult
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultMapper
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private val _catalogContent =
        MutableStateFlow<FetchResultUiState<CatalogFetchContent>>(FetchResultUiState.Loading())
    val catalogContent: StateFlow<FetchResultUiState<CatalogFetchContent>>
        get() = _catalogContent


    private val _favoriteResult =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val favoriteResult: StateFlow<FetchResultUiState<Unit>>
        get() = _favoriteResult

    fun addToFavorite(productId: Int) {
        updateState(_favoriteResult) { homeRepository.addToFavorites(productId) }
    }

    fun fetchContent() {
        updateState(_catalogContent) { homeRepository.fetchCatalogContent() }
    }

    private inline fun <T> updateState(
        stateFlow: MutableStateFlow<FetchResultUiState<T>>,
        crossinline fetch: suspend () -> FetchResult<T>
    ) {
        viewModelScope.launch {
            stateFlow.value = FetchResultUiState.Loading()
            stateFlow.value = fetch().map(FetchResultMapper())
        }
    }
}
