package com.radlance.matule.presentation.notification

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.notification.Notification
import com.radlance.matule.domain.notification.NotificationRepository
import com.radlance.matule.domain.remote.FetchResult
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
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

    private val notificationCount =
        notificationRepository.getNotificationsCount()

    fun fetchNotification() {
        viewModelScope.launch {
            updateFetchUiState(_fetchNotificationResult) {
                notificationRepository.loadNotifications()
            }
        }
    }

    fun markNotification(notification: Notification) {
        viewModelScope.launch {
            if (!notification.isRead) {
                val result = notificationRepository.setNotificationRead(notification.id)
                if (result is FetchResult.Success) {
                    notificationRepository.updateNotificationsCount(notificationCount.first().dec())
                    setNotificationRead(notification.id)
                }
            }
        }
    }

    private fun setNotificationRead(notificationId: Int) {
        updateLocalState(_fetchNotificationResult) { currentState ->
            updateCurrentNotificationsState(currentState, notificationId)
        }
    }

    private fun updateCurrentNotificationsState(
        currentState: FetchResultUiState.Success<List<Notification>>,
        notificationId: Int
    ) {
        val updatedNotifications = currentState.data.map { notification ->
            if (notification.id == notificationId) {
                notification.copy(isRead = true)
            } else {
                notification
            }
        }
        _fetchNotificationResult.value = FetchResultUiState.Success(updatedNotifications)
    }
}