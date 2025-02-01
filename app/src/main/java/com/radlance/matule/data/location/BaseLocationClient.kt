package com.radlance.matule.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.radlance.matule.R
import com.radlance.matule.common.ResourceManager
import com.radlance.matule.data.common.ContextCore
import com.radlance.matule.domain.location.LocationClient
import com.radlance.matule.domain.location.LocationClientResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BaseLocationClient @Inject constructor(
    private val context: Context,
    private val client: FusedLocationProviderClient,
    private val resourceManager: ResourceManager
) : LocationClient, ContextCore() {
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<LocationClientResult> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
                LocationClientResult.Error(
                    message = resourceManager.getString(R.string.missing_location_permission)
                )
            }

            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val isNetworkEnabled =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            if (!isGpsEnabled && !isNetworkEnabled) {
                LocationClientResult.Error(
                    message = resourceManager.getString(R.string.gps_is_disabled)
                )
            }

            val request = LocationRequest.Builder(interval).build()
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(LocationClientResult.Success(location)) }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
    }
}