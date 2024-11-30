package com.radlance.matule.presentation.home

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.home.HomeRepository
import com.radlance.matule.domain.remote.FetchResult
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultMapper
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    val catalogContent = homeRepository.fetchCatalogContent().mappedStateInViewModelWith(
        initialValue = FetchResultUiState.Loading()
    )

    fun switchFavoriteStatus(productId: Int) {
        viewModelScope.launch {
            homeRepository.switchFavoriteStatus(productId)
        }
    }

    private fun <T> Flow<FetchResult<T>>.mappedStateInViewModelWith(
        initialValue: FetchResultUiState<T>
    ): StateFlow<FetchResultUiState<T>> {
        return map { it.map(FetchResultMapper()) }.stateInViewModel(initialValue)
    }
}
