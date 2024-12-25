package com.radlance.matule.data.database.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val name: String,
    @SerialName("image_url") val imageUri: String
)
