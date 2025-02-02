package com.radlance.matule.navigation.base

import androidx.annotation.Keep
import com.radlance.matule.navigation.bottom.BottomDestination
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
data object Home : Destination

@Serializable
@Keep
data class Details(
    val productId: Int
) : Destination

@Serializable
@Keep
data class Catalog(
    val categoryId: Int?
) : Destination

@Serializable
@Keep
data object Popular : Destination

@Serializable
@Keep
data object Cart : Destination

@Serializable
@Keep
data object History : Destination

@Serializable
@Keep
data object Search : BottomDestination

@Serializable
@Keep
data object Notification : Destination

@Serializable
@Keep
data object Profile : Destination

@Serializable
@Keep
data object Payment : BottomDestination

@Serializable
@Keep
data object Order : BottomDestination

@Serializable
@Keep
data class Map(
    val latitude: Double,
    val longitude: Double
) : BottomDestination

@Serializable
@Keep
data object UserData : BottomDestination

@Serializable
@Keep
data object Barcode : BottomDestination

@Serializable
@Keep
data object EditProfile : BottomDestination