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
data object Favorite: BottomDestination