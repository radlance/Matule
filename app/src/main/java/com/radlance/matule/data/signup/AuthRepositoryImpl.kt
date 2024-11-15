package com.radlance.matule.data.signup

import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.authorization.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import java.net.UnknownHostException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val supabaseClient: SupabaseClient
) : AuthRepository {

    override suspend fun signUp(user: User): AuthResult {
        return try {
            supabaseClient.auth.signUpWith(Email) {
                email = user.email
                password = user.password
            }
            signIn(user)
        } catch (e: Exception) {
            AuthResult.Error(e is UnknownHostException)
        }
    }

    override suspend fun signIn(user: User): AuthResult {
        return try {
            supabaseClient.auth.signInWith(Email) {
                email = user.email
                password = user.password
            }
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e is UnknownHostException)
        }
    }
}