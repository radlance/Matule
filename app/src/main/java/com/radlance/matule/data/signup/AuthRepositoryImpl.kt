package com.radlance.matule.data.signup

import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.authorization.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.exceptions.HttpRequestException
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
        }
        catch (e: AuthRestException) {
            AuthResult.Error(statusCode = 422)
        }
        catch (e: Exception) {
            AuthResult.Error(noConnection = e is HttpRequestException)
        }
    }

    override suspend fun signIn(user: User): AuthResult {
        return try {
            supabaseClient.auth.signInWith(Email) {
                email = user.email
                password = user.password
            }
            AuthResult.Success
        }
        catch (e: AuthRestException) {
            AuthResult.Error(statusCode = 400)
        }
        catch (e: Exception) {
            AuthResult.Error(noConnection = e is HttpRequestException)
        }
    }

    override suspend fun sendOtp(email: String): AuthResult {
        return try {
            supabaseClient.auth.resetPasswordForEmail(email)
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(noConnection = e is HttpRequestException)
        }
    }

    override suspend fun updatePassword(newPassword: String): AuthResult {
        return try {
            supabaseClient.auth.updateUser {
                password = newPassword
            }
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e is UnknownHostException)
        }
    }
}