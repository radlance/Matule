package com.radlance.matule.presentation.home

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.home.CatalogFetchContent
import com.radlance.matule.domain.home.HomeRepository
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

    fun switchFavoriteStatus(productId: Int) {
        viewModelScope.launch {
            homeRepository.switchFavoriteStatus(productId)
        }
    }

    fun fetchContent() {
        viewModelScope.launch {
            _catalogContent.value = FetchResultUiState.Loading()
            val result = homeRepository.fetchCatalogContent()
            _catalogContent.value = result.map(FetchResultMapper())
        }
    }
}
