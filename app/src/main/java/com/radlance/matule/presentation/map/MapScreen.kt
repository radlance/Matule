package com.radlance.matule.presentation.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.matule.R
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider

@Composable
fun MapScreen(
    latitude: Double,
    longitude: Double,
    modifier: Modifier = Modifier,
    mapViewModel: MapViewModel = hiltViewModel()
) {
    var mapView by remember { mutableStateOf<MapView?>(null) }
    val mapKitState by mapViewModel.mapKitState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        snapshotFlow { mapView }.collect {
            it?.let {
                mapKitState.onStart()
                it.onStart()
            }
        }
    }

    AndroidView(
        factory = {
            MapView(it)
        },
        modifier = modifier.fillMaxSize()
    ) {
        it.mapWindow.map.mapObjects.addPlacemark().apply {
            geometry = Point(latitude, longitude)
            setIcon(ImageProvider.fromResource(context, R.drawable.ic_pin))
        }

        mapView = it
    }
}