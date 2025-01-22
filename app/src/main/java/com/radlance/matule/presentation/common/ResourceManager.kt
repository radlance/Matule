package com.radlance.matule.presentation.common

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes id: Int): String
}