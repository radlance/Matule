package com.radlance.matule.common

import androidx.annotation.StringRes

interface ResourceManager {
    fun getString(@StringRes id: Int): String
}