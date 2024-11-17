package com.radlance.matule.presentation.authorization.signin

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ForgotPasswordViewModel : ViewModel() {
    private val otpItems = mutableStateListOf("", "", "", "", "", "")

    fun getOtpItem(index: Int): String {
        return otpItems[index]
    }

    fun updateOtpItem(index: Int, value: String) {
        otpItems[index] = value
    }
}