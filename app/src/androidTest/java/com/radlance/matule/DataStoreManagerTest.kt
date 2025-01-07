package com.radlance.matule

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.radlance.matule.data.common.DataStoreRepository
import com.radlance.matule.data.common.DataStoreRepositoryImpl
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

const val TEST_DATASTORE_NAME: String = "test_datastore"
val context: Context = ApplicationProvider.getApplicationContext()

@OptIn(ExperimentalCoroutinesApi::class)
val dataStore = PreferenceDataStoreFactory.create(
    scope = CoroutineScope(UnconfinedTestDispatcher()),
    produceFile = { context.preferencesDataStoreFile(TEST_DATASTORE_NAME) }
)

@RunWith(AndroidJUnit4::class)
class DataStoreManagerTest {
    private lateinit var dataStoreRepository: DataStoreRepository

    @Before
    fun setup() {
        dataStoreRepository = DataStoreRepositoryImpl(dataStore)
    }

    @Test
    fun dataStore_setOnBoardingViewed_changeViewedStatusToTrue() = runTest {
        assertFalse(dataStoreRepository.getViewingStatus().first())
        dataStoreRepository.setOnBoardingViewed()
        assertTrue(dataStoreRepository.getViewingStatus().first())
    }

    @Test
    fun dataStore_setLoggedInStatus_updateCurrentValue() = runTest {
        assertFalse(dataStoreRepository.getLoggedInStatus().first())
        dataStoreRepository.setLoggedInStatus(true)
        assertTrue(dataStoreRepository.getLoggedInStatus().first())
        dataStoreRepository.setLoggedInStatus(false)
        assertFalse(dataStoreRepository.getLoggedInStatus().first())
    }
}