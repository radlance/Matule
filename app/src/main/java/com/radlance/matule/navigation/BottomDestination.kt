package com.radlance.matule.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

interface BottomDestination

@Serializable
@Keep
data object Base: BottomDestination

@Serializable
@Keep
data object Favorite: BottomDestination

@Serializable
@Keep
data object Bag: BottomDestination

@Serializable
@Keep
data object Notification: BottomDestination

@Serializable
@Keep
data object Profile: BottomDestination