package com.radlance.matule.domain.authorization

interface AuthRepository {
    suspend fun signUp(user: User): AuthResult
    suspend fun signIn(user: User): AuthResult
    suspend fun sendOtp(email: String): AuthResult
    suspend fun verifyEmailOtp(email: String, token: String): AuthResult
    suspend fun updatePassword(newPassword: String): AuthResult
}