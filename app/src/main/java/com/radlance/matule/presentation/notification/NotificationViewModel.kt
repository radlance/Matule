package com.radlance.matule.presentation.notification

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {
    private val _fetchNotificationResult =
        MutableStateFlow<FetchResultUiState<List<Notification>>>(FetchResultUiState.Initial())
    val fetchNotificationResult: StateFlow<FetchResultUiState<List<Notification>>> =
        _fetchNotificationResult.onStart {
            fetchNotification()
        }.stateInViewModel(FetchResultUiState.Initial())

    fun fetchNotification() {
        viewModelScope.launch {
            updateFetchUiState(_fetchNotificationResult) {
                notificationRepository.loadNotifications()
            }
        }
    }
}