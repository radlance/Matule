package com.radlance.matule.data.database.remote.entity

import kotlinx.serialization.Serializable

@Serializable
data class CategoryEntity(
    val id: Int,
    val title: String
)
