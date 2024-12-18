package com.radlance.matule.domain.user

import com.radlance.matule.domain.authorization.User

interface UserRepository {
    suspend fun getCurrentUserData(): User
}