package com.radlance.matule.domain.authorization

data class User(
    val email: String,
    val password: String,
    val name: String = ""
)
