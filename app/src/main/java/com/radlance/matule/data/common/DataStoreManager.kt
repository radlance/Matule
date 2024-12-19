package com.radlance.matule.data.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    suspend fun setOnBoardingViewed() {
        context.dataStore.edit { settings ->
            settings[KEY_ONBOARDING_VIEWED] = true
        }
    }

    fun getViewingStatus(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_ONBOARDING_VIEWED] ?: false
        }
    }

    suspend fun setLoggedInStatus(loggedIn: Boolean) {
        context.dataStore.edit { settings ->
            settings[KEY_USER_LOGGED_IN] = loggedIn
        }
    }

    fun getLoggedInStatus(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_USER_LOGGED_IN] ?: false
        }
    }

    private companion object {
        val KEY_ONBOARDING_VIEWED = booleanPreferencesKey("viewed")
        val KEY_USER_LOGGED_IN = booleanPreferencesKey("logged in")
    }
}