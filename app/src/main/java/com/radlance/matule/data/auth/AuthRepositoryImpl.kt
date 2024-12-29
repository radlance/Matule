package com.radlance.matule.data.auth

import android.util.Log
import com.radlance.matule.domain.authorization.AuthRepository
import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.domain.user.User
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.providers.builtin.IDToken
import io.github.jan.supabase.exceptions.HttpRequestException
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthRepository {

    override suspend fun signUp(user: User): AuthResult {
        return try {
            auth.signUpWith(Email) {
                email = user.email
                password = user.password
                data = buildJsonObject {
                    put("name", user.name)
                    put(
                        "avatar_url",
                        "https://osoknxtwcppulkimwpjo.supabase.co/storage/v1/object/public/Matule/default_profile_image.jpg"
                    )
                }
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

    override suspend fun signInWithGoogle(googleIdToken: String, rawNonce: String): AuthResult {
        return try {
            auth.signInWith(IDToken) {
                idToken = googleIdToken
                provider = Google
                nonce = rawNonce
            }
            AuthResult.Success
        } catch (e: Exception) {
            Log.d("AuthRepositoryImpl", e.message!!)
            AuthResult.Error()
        }
    }

    override suspend fun signIn(user: User): AuthResult {
        return try {
            auth.signInWith(Email) {
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
            auth.resetPasswordForEmail(email)
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(noConnection = e is HttpRequestException)
        }
    }

    override suspend fun verifyEmailOtp(email: String, token: String): AuthResult {
        return try {
            auth.verifyEmailOtp(
                type = OtpType.Email.EMAIL,
                email = email,
                token = token
            )
            AuthResult.Success
        } catch (e: AuthRestException) {
            AuthResult.Error(statusCode = 403)
        } catch (e: Exception) {
            AuthResult.Error(noConnection = e is HttpRequestException)
        }
    }

    override suspend fun updatePassword(newPassword: String): AuthResult {
        return try {
            auth.updateUser {
                password = newPassword
            }
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e is HttpRequestException)
        }
    }

    override suspend fun signOut(): AuthResult {
        return try {
            auth.signOut()
            AuthResult.Success
        } catch (e: Exception) {
            AuthResult.Error(e is HttpRequestException)
        }
    }
}