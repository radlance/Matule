package com.radlance.matule.data.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreRepository {
    override suspend fun setOnBoardingViewed(viewed: Boolean) {
        dataStore.edit { settings ->
            settings[KEY_ONBOARDING_VIEWED] = viewed
        }
    }

    override fun getViewingStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[KEY_ONBOARDING_VIEWED] ?: false
        }
    }

    override suspend fun setLoggedInStatus(loggedIn: Boolean) {
        dataStore.edit { settings ->
            settings[KEY_USER_LOGGED_IN] = loggedIn
        }
    }

    override fun getLoggedInStatus(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[KEY_USER_LOGGED_IN] ?: false
        }
    }

    override fun getNotificationsCount(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[KEY_NOTIFICATION_COUNT] ?: 0
        }
    }

    override suspend fun updateNotificationCount(notificationsCount: Int) {
        dataStore.edit { settings ->
            settings[KEY_NOTIFICATION_COUNT] = notificationsCount
        }
    }

    private companion object {
        val KEY_ONBOARDING_VIEWED = booleanPreferencesKey("viewed")
        val KEY_USER_LOGGED_IN = booleanPreferencesKey("logged_in")
        val KEY_NOTIFICATION_COUNT = intPreferencesKey("notification count")
    }
}