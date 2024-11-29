package com.radlance.matule.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.presentation.common.FetchResultMapper
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    val categories = homeRepository.getCategories()
        .map { it.map(FetchResultMapper()) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = FetchResultUiState.Loading()
        )

    val products = homeRepository.getProducts()
        .map { it.map(FetchResultMapper()) }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = FetchResultUiState.Loading()
        )

    fun switchFavoriteStatus(productId: Int) {
        viewModelScope.launch {
            homeRepository.switchFavoriteStatus(productId)
        }
    }
}
