package com.radlance.matule.di

import android.content.Context
import com.radlance.matule.data.database.MatuleDao
import com.radlance.matule.data.database.MatuleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MatuleDatabase {
        return MatuleDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideDao(matuleDatabase: MatuleDatabase): MatuleDao {
        return matuleDatabase.dao()
    }
}