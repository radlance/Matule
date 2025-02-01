package com.radlance.matule.domain.location

import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<LocationClientResult>
}