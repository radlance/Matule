package com.radlance.matule.presentation.authorization.signin

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radlance.matule.R
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.presentation.authorization.common.AuthResultUiState
import com.radlance.matule.presentation.common.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val mapper: AuthResult.Mapper<AuthResultUiState>,
    private val resourceManager: ResourceManager
) : ViewModel() {
    private val otpItems = mutableStateListOf("", "", "", "", "", "")
    private val _resendingUiState = MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)
    val resendingUiState: StateFlow<AuthResultUiState>
        get() = _resendingUiState.asStateFlow()

    private val _savePasswordUiState =
        MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)
    val savePasswordUiState: StateFlow<AuthResultUiState>
        get() = _savePasswordUiState.asStateFlow()

    private val _confirmOtpState =
        MutableStateFlow<AuthResultUiState>(AuthResultUiState.Initial)
    val confirmOtpState: StateFlow<AuthResultUiState>
        get() = _confirmOtpState.asStateFlow()

    private val _verificationUiState = MutableStateFlow(VerificationUiState())
    val verificationUiState: StateFlow<VerificationUiState>
        get() = _verificationUiState.asStateFlow()

    fun getOtpItem(index: Int): String {
        return otpItems[index]
    }

    fun updateOtpItem(index: Int, value: String, email: String) {
        otpItems[index] = value
        if (otpItems.all { it.isNotEmpty() }) {
            confirmOtp(email, otpItems.joinToString(""))

            _verificationUiState.update { currentState ->
                currentState.copy(hideKeyBoard = true)
            }
        }
    }

    fun showRecoveryDialog() {
        _verificationUiState.update { currentState ->
            currentState.copy(showRecoveryDialog = true)
        }
    }

    fun resendOtp(email: String) {
        viewModelScope.launch {
            _resendingUiState.value = AuthResultUiState.Loading(
                resourceManager.getString(R.string.sending)
            )

            val result = authRepository.sendOtp(email)
            _resendingUiState.value = result.map(mapper)
        }
    }

    fun updateUserPassword(newPassword: String) {
        _verificationUiState.update { currentState ->
            currentState.copy(showRecoveryDialog = false)
        }

        viewModelScope.launch {
            _savePasswordUiState.value = AuthResultUiState.Loading(
                resourceManager.getString(R.string.saving)
            )

            val result = authRepository.updatePassword(newPassword)
            _savePasswordUiState.value = result.map(mapper)
        }
    }

    private fun confirmOtp(email: String, otp: String) {
        viewModelScope.launch {
            _confirmOtpState.value = AuthResultUiState.Loading(
                resourceManager.getString(R.string.checking)
            )

            val result = authRepository.verifyEmailOtp(email = email, token = otp)
            _confirmOtpState.value = result.map(mapper)
        }
    }

    fun generatePassword() {
        _verificationUiState.update { currentState ->
            currentState.copy(currentPassword = currentState.currentPassword.toPassword())
        }
    }

    fun updateCurrentPassword(updatedPassword: String) {
        _verificationUiState.update { currentState ->
            currentState.copy(currentPassword = updatedPassword)
        }
    }
}

private fun String.toPassword(): String {
    val latinPhrase = lowercase()
        .replace("а", "a")
        .replace("б", "b")
        .replace("в", "v")
        .replace("г", "g")
        .replace("д", "d")
        .replace("е", "e")
        .replace("ё", "e")
        .replace("ж", "zh")
        .replace("з", "z")
        .replace("и", "i")
        .replace("й", "j")
        .replace("к", "k")
        .replace("л", "l")
        .replace("м", "m")
        .replace("н", "n")
        .replace("о", "o")
        .replace("п", "p")
        .replace("р", "r")
        .replace("с", "s")
        .replace("т", "t")
        .replace("у", "u")
        .replace("ф", "f")
        .replace("х", "h")
        .replace("ц", "c")
        .replace("ч", "ch")
        .replace("ш", "sh")
        .replace("щ", "sch")
        .replace("ъ", "")
        .replace("ы", "y")
        .replace("ь", "")
        .replace("э", "e")
        .replace("ю", "yu")
        .replace("я", "ya")

    val modifiedPhrase = latinPhrase
        .replace("i", "1")
        .replace("o", "0")
        .replace("s", "5")
        .replace("e", "3")
        .replace("a", "4")
        .replace("t", "7")
        .replace("g", "9")
        .replace("z", "2")
        .replace(" ", "_")

    val passwordBuilder = StringBuilder(modifiedPhrase)

    for (i in passwordBuilder.indices) {
        if (Random.nextBoolean()) {
            passwordBuilder.setCharAt(i, passwordBuilder[i].uppercaseChar())
        }
    }

    val specialChars = listOf(
        '!',
        '@',
        '#',
        '$',
        '%',
        '^',
        '&',
        '*',
        '(',
        ')',
        '-',
        '_',
        '+',
        '=',
        '[',
        ']',
        '{',
        '}',
        '|',
        ';',
        ':',
        '<',
        '>',
        '.',
        ',',
        '?',
        '/',
        '`',
        '~'
    )

    for (i in modifiedPhrase.indices step 4) {
        if (i < modifiedPhrase.length) {
            passwordBuilder.insert(i, specialChars.random())
        }
    }

    while (passwordBuilder.length < 8) {
        passwordBuilder.append(specialChars.random())
    }

    return passwordBuilder.toString()
}