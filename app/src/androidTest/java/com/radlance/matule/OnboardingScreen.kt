package com.radlance.matule

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingScreen(
    @StringRes val stringResId: Int,
    @DrawableRes val imageContentDescription: Int
)
