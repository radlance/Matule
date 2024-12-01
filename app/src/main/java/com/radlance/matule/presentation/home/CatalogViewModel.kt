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
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val favoriteResult: StateFlow<FetchResultUiState<Int>>
        get() = _favoriteResult

    init {
        fetchContent()
    }

    fun changeFavoriteStatus(productId: Int) {

        updateState(stateFlow = _favoriteResult, loadingData = productId) {
            homeRepository.changeFavoriteStatus(productId)
        }
    }

    fun changeStateFavoriteStatus(productId: Int) {
        val currentState = _catalogContent.value
        if (currentState is FetchResultUiState.Success) {
            changeFavoriteByResult(currentState, productId)
        }
    }

    private fun changeFavoriteByResult(
        currentState: FetchResultUiState.Success<CatalogFetchContent>,
        productId: Int
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            if (product.id == productId) {
                product.copy(isFavorite = !product.isFavorite)
            } else {
                product
            }
        }
        val updatedContent = currentState.data.copy(products = updatedProducts)
        _catalogContent.value = FetchResultUiState.Success(updatedContent)
    }

    fun fetchContent() {
        updateState(_catalogContent) { homeRepository.fetchCatalogContent() }
    }

    private inline fun <T> updateState(
        stateFlow: MutableStateFlow<FetchResultUiState<T>>,
        loadingData: T? = null,
        crossinline fetch: suspend () -> FetchResult<T>
    ) {
        viewModelScope.launch {
            stateFlow.value = FetchResultUiState.Loading(loadingData)
            stateFlow.value = fetch().map(FetchResultMapper())
        }
    }
}
