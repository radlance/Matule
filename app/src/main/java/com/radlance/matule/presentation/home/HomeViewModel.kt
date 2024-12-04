package com.radlance.matule.presentation.home

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.home.CatalogFetchContent
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.home.Product
import com.radlance.matule.domain.remote.FetchResult
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultMapper
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    private val _catalogContent =
        MutableStateFlow<FetchResultUiState<CatalogFetchContent>>(FetchResultUiState.Loading())
    val catalogContent: StateFlow<FetchResultUiState<CatalogFetchContent>>
        get() = _catalogContent.asStateFlow()


    private val _favoriteResult =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val favoriteResult: StateFlow<FetchResultUiState<Int>>
        get() = _favoriteResult.asStateFlow()

    private val _inCartResult =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val inCartResult: StateFlow<FetchResultUiState<Int>>
        get() = _inCartResult.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?>
        get() = _selectedProduct.asStateFlow()

    init {
        fetchContent()
    }

    fun changeFavoriteStatus(productId: Int) {
        updateState(stateFlow = _favoriteResult, loadingData = productId) {
            homeRepository.changeFavoriteStatus(productId)
        }
    }

    fun addProductToCart(productId: Int) {
        updateState(stateFlow = _inCartResult, loadingData = productId) {
            homeRepository.addProductToCart(productId)
        }
    }

    fun changeStateInCartStatus(productId: Int) {
        val currentState = _catalogContent.value
        if (currentState is FetchResultUiState.Success) {
            changeInCartByResult(currentState, productId)
        }
    }

    fun changeStateFavoriteStatus(productId: Int) {
        val currentState = _catalogContent.value
        if (currentState is FetchResultUiState.Success) {
            changeFavoriteByResult(currentState, productId)
        }
    }

    private fun changeInCartByResult(
        currentState: FetchResultUiState.Success<CatalogFetchContent>,
        productId: Int
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            if (product.id == productId) {
                product.copy(inCart = true)
            } else {
                product
            }
        }

        val updatedContent = currentState.data.copy(products = updatedProducts)
        _catalogContent.value = FetchResultUiState.Success(updatedContent)
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

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
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
