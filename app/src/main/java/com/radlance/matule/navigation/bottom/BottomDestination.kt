package com.radlance.matule.navigation.bottom

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

interface BottomDestination

@Serializable
@Keep
data object Base: BottomDestination

@Serializable
@Keep
data object Catalog : BottomDestination

@Serializable
@Keep
data class Details(
    val productId: Int
) : BottomDestination

@Serializable
@Keep
data object Favorite: BottomDestination

@Serializable
@Keep
data object Payment: BottomDestination

@Serializable
@Keep
data object Cart: BottomDestination

@Serializable
@Keep
data object Order: BottomDestination

@Serializable
@Keep
data object Notification: BottomDestination

@Serializable
@Keep
data object Profile: BottomDestination

@Serializable
@Keep
data object FullScreenBarcode: BottomDestination