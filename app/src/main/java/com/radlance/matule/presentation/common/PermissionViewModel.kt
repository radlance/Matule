package com.radlance.matule.presentation.common

import com.radlance.matule.R
import com.radlance.matule.common.ResourceManager
import com.radlance.matule.domain.location.LocationClient
import com.radlance.matule.domain.location.LocationClientResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(
    locationClient: LocationClient,
    mapper: LocationClientResult.Mapper<LocationClientResultUiState>,
    resourceManager: ResourceManager,
) : BaseViewModel() {
    val locationClientUiState = locationClient.getLocationUpdates(5000L).map { result ->
        result.map(mapper)
    }.stateInViewModel(
        initialValue = LocationClientResultUiState.Initial(
            resourceManager.getString(
                R.string.getting_current_position
            )
        )
    )
}