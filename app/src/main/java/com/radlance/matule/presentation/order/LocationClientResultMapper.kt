package com.radlance.matule.presentation.order

import android.location.Location
import com.radlance.matule.domain.location.LocationClientResult
import com.radlance.matule.presentation.common.LocationClientResultUiState
import javax.inject.Inject

class LocationClientResultMapper @Inject constructor(
) : LocationClientResult.Mapper<LocationClientResultUiState> {
    override fun mapSuccess(location: Location): LocationClientResultUiState {
        return LocationClientResultUiState.Success(location)
    }

    override fun mapError(message: String): LocationClientResultUiState {
        return LocationClientResultUiState.Error(message)
    }
}