package com.radlance.matule

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.radlance.matule.data.auth.AuthRepositoryImpl
import com.radlance.matule.data.common.DataStoreRepositoryImpl
import com.radlance.matule.data.onboarding.NavigationRepositoryImpl
import com.radlance.matule.navigation.base.NavigationViewModel
import com.radlance.matule.presentation.authorization.common.AuthResultMapper
import com.radlance.matule.presentation.authorization.common.AuthViewModel
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.serializer.KotlinXSerializer
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.test.TestScope

class ViewModelFactory(private val context: Context) {
    fun createNavigationViewModel(): NavigationViewModel {
        val testDataStore = PreferenceDataStoreFactory.create(
            scope = TestScope(),
            produceFile = { context.preferencesDataStoreFile(TEST_ONBOARDING_DATASTORE) }
        )

        val dataStoreRepositoryImpl = DataStoreRepositoryImpl(testDataStore)
        val navigationRepository = NavigationRepositoryImpl(dataStoreRepositoryImpl)
        val navigationViewModel = NavigationViewModel(navigationRepository)

        return navigationViewModel
    }

    fun createAuthViewModel(): AuthViewModel {
        val supabaseClient = createSupabaseClient(
            supabaseUrl = "https://osoknxtwcppulkimwpjo.supabase.co",
            supabaseKey = BuildConfig.SUPABASE_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
            defaultSerializer = KotlinXSerializer()
        }

        val authRepository = AuthRepositoryImpl(supabaseClient.auth)
        val mapper = AuthResultMapper()

        val authViewModel = AuthViewModel(authRepository, mapper)

        return authViewModel
    }

    private companion object {
        const val TEST_ONBOARDING_DATASTORE: String = "test_onboarding_datastore"
    }
}