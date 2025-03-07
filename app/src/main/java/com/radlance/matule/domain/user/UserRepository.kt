package com.radlance.matule.domain.user

import com.radlance.matule.domain.remote.FetchResult

interface UserRepository {
    suspend fun getCurrentUserData(): FetchResult<User>
    suspend fun updateUserData(user: User, imageByteArray: ByteArray?): FetchResult<Unit>
}