package com.radlance.matule.navigation.base

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

interface Destination

@Serializable
@Keep
data object Splash: Destination

@Serializable
@Keep
data object OnboardingFirst: Destination

@Serializable
@Keep
data object OnboardingSecond: Destination

@Serializable
@Keep
data object OnboardingThird: Destination

@Serializable
@Keep
data object SignIn: Destination

@Serializable
@Keep
data object SignUp: Destination

@Serializable
@Keep
data object ForgotPassword: Destination

@Serializable
@Keep
data class Verification(
    val email: String
): Destination

@Serializable
@Keep
data object Home: Destination