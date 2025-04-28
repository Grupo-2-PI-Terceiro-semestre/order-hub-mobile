package com.example.app_orderhub.ui.map

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.app_orderhub.navigation.MenuNavigation
import com.example.app_orderhub.ui.map.viewmodel.MapViewModel
import com.example.app_orderhub.util.theme.ColorBackGroundDefault
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PointOfInterest
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState


@Composable
fun MapScreen(navController: NavController, viewModel: MapViewModel = viewModel()) {
    val locations by viewModel.locations.collectAsState()
    val names by viewModel.name.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()

    var selectedPoi by remember {
        mutableStateOf<PointOfInterest?>(null)
    }

    LaunchedEffect(Unit) {
        viewModel.getUserLocationAndFetchLocations()
    }

    val cameraPositionState = rememberCameraPositionState {
        position = userLocation?.let {
            CameraPosition.fromLatLngZoom(it, 15f)
        } ?: CameraPosition.fromLatLngZoom(LatLng(-23.55052, -46.633308), 12f)
    }

    LaunchedEffect(userLocation) {
        userLocation?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
        }
    }

    MenuNavigation(navController) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(ColorBackGroundDefault)) {
            GoogleMap(
                cameraPositionState = cameraPositionState,
                modifier = Modifier.fillMaxSize(),
                onPOIClick = { poi ->
                    Log.d("MapScreen", "Novo POI selecionado: ${poi.name}, ${poi.latLng}")
                    selectedPoi = PointOfInterest(poi.latLng, poi.placeId, poi.name)
                }
            ) {

                locations.zip(names).forEach { (location, name) ->
                    Marker(
                        state = rememberMarkerState(position = location),
                        title = name
                    )
                }

                userLocation?.let {
                    Marker(
                        state = rememberMarkerState(position = it),
                        title = "Você está aqui",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
                }

                selectedPoi?.let { poi ->
                    Marker(
                        state = rememberMarkerState(position = poi.latLng),
                        title = poi.name,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    )
                }
            }
        }
    }
}


