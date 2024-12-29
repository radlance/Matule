package com.radlance.matule.data.user

import android.util.Log
import com.radlance.matule.data.database.remote.RemoteMapper
import com.radlance.matule.data.database.remote.entity.UserEntity
import com.radlance.matule.domain.remote.FetchResult
import com.radlance.matule.domain.user.User
import com.radlance.matule.domain.user.UserRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.storage.storage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.put
import java.util.UUID
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : UserRepository, RemoteMapper() {
    override suspend fun getCurrentUserData(): User {
        val currentUser = supabaseClient.auth.currentSessionOrNull()?.user
        val json = Json { ignoreUnknownKeys = true }
        val userData = currentUser?.userMetadata?.let {
            json.decodeFromString<UserEntity>(
                it.toString()
            ).toUser()
        }

        return userData?.copy(email = currentUser.email ?: "") ?: User()
    }

    override suspend fun updateUserData(
        user: User,
        imageByteArray: ByteArray?
    ): FetchResult<Unit> {
        val currentUser = supabaseClient.auth.currentSessionOrNull()?.user
        return try {
            if (currentUser != null) {
                imageByteArray?.let {
                    val fileName =
                        "profile_image_${UUID.randomUUID().toString().replace("-", "")}.jpg"
                    val bucket = supabaseClient.storage["Matule"]
                    bucket.upload(fileName, imageByteArray, options = { upsert = true })
                    val savedImageUrl = bucket.publicUrl(fileName)
                    supabaseClient.auth.updateUser {
                        data {
                            put("avatar_url", savedImageUrl)
                        }
                    }
                }

                supabaseClient.auth.updateUser {
                    data {
                        put("name", user.name)
                    }
                }
                FetchResult.Success(Unit)
            } else {
                Log.e("UserRepositoryImpl", "user was null")
                FetchResult.Error(Unit)
            }
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", e.message!!)
            FetchResult.Error(Unit)
        }
    }
}