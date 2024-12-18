package com.radlance.matule.presentation.common

import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.product.CatalogFetchContent
import com.radlance.matule.domain.product.Product
import com.radlance.matule.domain.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _historyResult =
        MutableStateFlow<FetchResultUiState<List<HistoryProduct>>>(FetchResultUiState.Initial())
    val historyResult: StateFlow<FetchResultUiState<List<HistoryProduct>>> =
        _historyResult.onStart { fetchHistory() }.stateInViewModel(
            FetchResultUiState.Loading()
        )

    private val _catalogContent =
        MutableStateFlow<FetchResultUiState<CatalogFetchContent>>(FetchResultUiState.Loading())
    val catalogContent: StateFlow<FetchResultUiState<CatalogFetchContent>> =
        _catalogContent.onStart { fetchContent() }.stateInViewModel(
            FetchResultUiState.Loading()
        )

    private val _placeOrderResult =
        MutableStateFlow<FetchResultUiState<List<Product>>>(FetchResultUiState.Initial())
    val placeOrderResult: StateFlow<FetchResultUiState<List<Product>>>
        get() = _placeOrderResult.asStateFlow()

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

    private val _removeResult =
        MutableStateFlow<FetchResultUiState<Int>>(FetchResultUiState.Initial())
    val removeResult: StateFlow<FetchResultUiState<Int>>
        get() = _removeResult.asStateFlow()

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?>
        get() = _selectedProduct.asStateFlow()

    private val removedProductQuantity = MutableStateFlow(0)

    fun fetchHistory() {
        updateState(_historyResult) { productRepository.loadHistory() }
    }

    fun fetchContent() {
        updateState(_catalogContent) { productRepository.fetchCatalogContent() }
    }

    fun changeFavoriteStatus(productId: Int) {
        updateState(stateFlow = _favoriteResult, loadingData = productId) {
            productRepository.changeFavoriteStatus(productId)
        }
    }

    fun addProductToCart(productId: Int) {
        updateState(stateFlow = _inCartResult, loadingData = productId) {
            productRepository.addProductToCart(productId)
        }
    }

    fun updateProductQuantity(productId: Int, currentQuantity: Int) {
        updateState(stateFlow = _quantityResult, loadingData = productId) {
            productRepository.updateQuantity(productId, currentQuantity)
        }
    }

    fun removeProductFromCart(productId: Int) {
        updateState(stateFlow = _removeResult, loadingData = productId) {
            productRepository.removeProductFromCart(productId)
        }
    }

    fun placeOrder(products: List<Product>) {
        updateState(stateFlow = _placeOrderResult) {
            productRepository.placeOrder(products)
        }
    }

    fun resetPlaceOrderResult() {
        _placeOrderResult.value = FetchResultUiState.Initial()
    }

    fun changeStateInCartStatus(productId: Int, recover: Boolean = false) {
        updateLocalState(_catalogContent) { currentState ->
            changeInCartByResult(currentState, productId, recover)
        }
    }

    fun changeStateFavoriteStatus(productId: Int) {
        updateLocalState(_catalogContent) { currentState ->
            changeFavoriteByResult(currentState, productId)
        }
    }

    fun updateCurrentQuantity(productId: Int, increment: Boolean) {
        updateLocalState(_catalogContent) { currentState ->
            updateCartItemQuantity(currentState, productId, increment)
        }
    }

    fun deleteCartItemFromCurrentState(productId: Int, recover: Boolean = false) {
        updateLocalState(_catalogContent) { currentState ->
            deleteCartItem(currentState, productId, recover)
        }
    }

    fun updateStateAfterPlaceOrder(order: List<Product>) {
        updateLocalState(_catalogContent) { currentState ->
            updateCartItemsAfterPlaceOrder(currentState)
        }

        updateLocalState(_historyResult) { currentState ->
            saveOrderToHistory(currentState, order)
        }
    }

    private fun changeInCartByResult(
        currentState: FetchResultUiState.Success<CatalogFetchContent>,
        productId: Int,
        recover: Boolean
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            if (product.id == productId) {
                val newQuantity = if(recover) 0 else 1
                product.copy(quantityInCart = newQuantity)
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

    private fun deleteCartItem(
        currentState: FetchResultUiState.Success<CatalogFetchContent>,
        productId: Int,
        recover: Boolean
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            if (product.id == productId) {
                if(!recover) {
                    removedProductQuantity.value = product.quantityInCart
                    product.copy(quantityInCart = 0)
                } else {
                    product.copy(quantityInCart = removedProductQuantity.value)
                }
            } else {
                product
            }
        }
        val updatedContent = currentState.data.copy(products = updatedProducts)
        _catalogContent.value = FetchResultUiState.Success(updatedContent)
    }

    private fun updateCartItemsAfterPlaceOrder(
        currentState: FetchResultUiState.Success<CatalogFetchContent>
    ) {
        val updatedProducts = currentState.data.products.map { product ->
            product.copy(quantityInCart = 0)
        }
        val updatedContent = currentState.data.copy(products = updatedProducts)
        _catalogContent.value = FetchResultUiState.Success(updatedContent)
    }

    private fun saveOrderToHistory(
        currentState: FetchResultUiState.Success<List<HistoryProduct>>,
        products: List<Product>
    ) {
        val currentHistory = currentState.data.toMutableList()
        currentHistory.addAll(
            products.map {
                with(it) {
                    HistoryProduct(
                        id = id,
                        title = title,
                        price = price,
                        imageUrl = imageUrl,
                        orderTime = LocalDateTime.now().toKotlinLocalDateTime()
                    )
                }
            }
        )

        _historyResult.value = currentState.copy(data = currentHistory)
    }


    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }

    private inline fun <T> updateLocalState(
        state: StateFlow<FetchResultUiState<T>>,
        action: (FetchResultUiState.Success<T>) -> Unit
    ) {
        val value = state.value

        if (value is FetchResultUiState.Success) {
            action(value)
        }
    }
}
