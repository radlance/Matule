package com.radlance.matule.presentation.profile

import androidx.lifecycle.viewModelScope
import com.radlance.matule.domain.user.User
import com.radlance.matule.domain.user.UserRepository
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userData = MutableStateFlow(User())
    val userData: StateFlow<User> = _userData.onStart {
        _userData.value = userRepository.getCurrentUserData()
    }.stateInViewModel(User())

    private val _uploadImageResult =
        MutableStateFlow<FetchResultUiState<String>>(FetchResultUiState.Initial())

    val uploadImageResult: StateFlow<FetchResultUiState<String>>
        get() = _uploadImageResult.asStateFlow()

    fun uploadUserImageUri(imageByteArray: ByteArray) {
        viewModelScope.launch {
            updateFetchUiState(_uploadImageResult) { userRepository.uploadImage(imageByteArray) }
        }
    }
}