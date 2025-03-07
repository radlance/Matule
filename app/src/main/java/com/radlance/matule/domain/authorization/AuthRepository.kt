package com.radlance.matule.domain.authorization

import com.radlance.matule.domain.user.User

interface AuthRepository {
    suspend fun signUp(user: User): AuthResult
    suspend fun signInWithGoogle(googleIdToken: String, rawNonce: String): AuthResult
    suspend fun signIn(user: User): AuthResult
    suspend fun sendOtp(email: String): AuthResult
    suspend fun verifyEmailOtp(email: String, token: String): AuthResult
    suspend fun updatePassword(newPassword: String): AuthResult
    suspend fun signOut(): AuthResult
}