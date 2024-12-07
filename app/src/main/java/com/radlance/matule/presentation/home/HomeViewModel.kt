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

    private val _quantityResult =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val quantityResult: StateFlow<FetchResultUiState<Int>>
        get() = _quantityResult.asStateFlow()

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

    fun updateProductQuantity(productId: Int, currentQuantity: Int) {
        updateState(stateFlow = _quantityResult, loadingData = productId) {
            homeRepository.updateQuantity(productId, currentQuantity)
        }
    }

    fun changeStateInCartStatus(productId: Int) {
        updateCatalogState(productId) { currentState, id ->
            changeInCartByResult(currentState, id)
        }
    }

    fun changeStateFavoriteStatus(productId: Int) {
        updateCatalogState(productId) { currentState, id ->
            changeFavoriteByResult(currentState, id)
        }
    }

    fun updateCurrentQuantity(productId: Int, increment: Boolean) {
        updateCatalogState(productId) { currentState, id ->
            updateCartItemQuantity(currentState, id, increment)
        }
    }

    private fun changeInCartByResult(
        currentState: FetchResultUiState.Success<CatalogFetchContent>,
        productId: Int
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            if (product.id == productId) {
                product.copy(quantityInCart = 1)
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

    private fun updateCartItemQuantity(
        currentState: FetchResultUiState.Success<CatalogFetchContent>,
        productId: Int,
        increment: Boolean
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            if (product.id == productId) {
                val newQuantity =
                    if (increment) product.quantityInCart.inc() else product.quantityInCart.dec()
                product.copy(quantityInCart = newQuantity)
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

    private inline fun updateCatalogState(
        productId: Int,
        action: (FetchResultUiState.Success<CatalogFetchContent>, Int) -> Unit
    ) {
        val currentState = _catalogContent.value
        if (currentState is FetchResultUiState.Success) {
            action(currentState, productId)
        }
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
