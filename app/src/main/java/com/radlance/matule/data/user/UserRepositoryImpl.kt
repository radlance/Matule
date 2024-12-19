package com.radlance.matule.data.user

import com.radlance.matule.data.common.Mapper
import com.radlance.matule.data.database.remote.entity.UserEntity
import com.radlance.matule.domain.authorization.User
import com.radlance.matule.domain.user.UserRepository
import io.github.jan.supabase.auth.Auth
import kotlinx.serialization.json.Json
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val auth: Auth
) : UserRepository, Mapper() {
    override suspend fun getCurrentUserData(): User {
        val currentUser = auth.currentUserOrNull()
        val json = Json { ignoreUnknownKeys = true }
        val userData = currentUser?.userMetadata?.let {
            json.decodeFromString<UserEntity>(
                it.toString()
            ).toUser()
        }

        return userData?.copy(email = currentUser.email ?: "") ?: User()
    }
}