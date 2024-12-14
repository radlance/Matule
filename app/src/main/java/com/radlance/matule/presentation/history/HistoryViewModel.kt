package com.radlance.matule.presentation.history

import com.radlance.matule.domain.history.HistoryProduct
import com.radlance.matule.domain.history.HistoryRepository
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : BaseViewModel() {
    private val _historyResult =
        MutableStateFlow<FetchResultUiState<List<HistoryProduct>>>(FetchResultUiState.Initial())
    val historyResult: StateFlow<FetchResultUiState<List<HistoryProduct>>> =
        _historyResult.onStart { fetchHistory() }.stateInViewModel(
            FetchResultUiState.Loading()
        )

    fun fetchHistory() {
        updateState(_historyResult) { historyRepository.loadHistory() }
    }
}