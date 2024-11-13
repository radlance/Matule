package com.radlance.matule.di

import com.radlance.matule.domain.authorization.AuthResult
import com.radlance.matule.presentation.signup.SignUpResultMapper
import com.radlance.matule.presentation.signup.SignUpResultUiState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SignUpModule {
    @Binds
    fun provideMapper(mapper: SignUpResultMapper): AuthResult.Mapper<SignUpResultUiState>
}