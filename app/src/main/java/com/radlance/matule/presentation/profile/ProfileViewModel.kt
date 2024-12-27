package com.radlance.matule.presentation.profile

import android.util.Log
import com.radlance.matule.domain.user.User
import com.radlance.matule.domain.user.UserRepository
import com.radlance.matule.presentation.common.BaseViewModel
import com.radlance.matule.presentation.common.FetchResultUiState
import com.radlance.matule.presentation.profile.edit.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userData = MutableStateFlow(User())
    val userData: StateFlow<User> = _userData.onStart {
        _userData.value = userRepository.getCurrentUserData()
    }.stateInViewModel(User())

    private val _updateUserResult =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val updateUserResult: StateFlow<FetchResultUiState<Unit>>
        get() = _updateUserResult.asStateFlow()

    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState>
        get() = _profileUiState.asStateFlow()

    fun saveProfileChanges(name: String, email: String, imageByteArray: ByteArray?) {
        validateFields(name, email)

        with(profileUiState.value) {
            if (isValidName && isValidEmail) {
                val user = User(name = name, email = email)
                Log.d("ProfileViewModel", "updated user: $user")
                updateFetchUiState(_updateUserResult) {
                    userRepository.updateUserData(user, imageByteArray)
                }
            }
        }
    }

    private fun validateFields(name: String, email: String) {
        _profileUiState.update { currentState ->
            currentState.copy(
                isValidName = name.isNotBlank(),
                isValidEmail = Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$").matches(email)
            )
        }
    }

    fun resetNameError() {
        _profileUiState.update { currentState ->
            currentState.copy(isValidName = true)
        }
    }

    fun resetEmailError() {
        _profileUiState.update { currentState ->
            currentState.copy(isValidEmail = true)
        }
    }

    fun updateActionButtonState(isEnabled: Boolean) {
        _profileUiState.update { currentState ->
            currentState.copy(isEnabledButton = isEnabled)
        }
    }
}