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
import com.example.app_orderhub.domain.model.Address
import com.example.app_orderhub.domain.model.Enterprise
import com.example.app_orderhub.domain.model.Service
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
import java.time.LocalTime
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt


@Composable
fun MapScreen(navController: NavController, viewModel: MapViewModel = viewModel()) {
    val locations by viewModel.locations.collectAsState()
    val names by viewModel.name.collectAsState()
    val userLocation by viewModel.userLocation.collectAsState()

    var selectedPoi by remember {
        mutableStateOf<PointOfInterest?>(null)
    }

//    LaunchedEffect(Unit) {
//        viewModel.setLocations(initEnterprises())
//    }

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

                // Marcador do usuário
                userLocation?.let {
                    Marker(
                        state = rememberMarkerState(position = it),
                        title = "Você está aqui",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
                }

                // Marcador vermelho (recriado quando markerKey muda)
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


//fun initEnterprises() = listOf(
//    Enterprise(
//        1,
//        nomeEmpresa = "Barbearia Alpha",
//        endereco = Address(
//            cep = "22250-040",
//            logradouro = "Praia de Botafogo",
//            numero = "300",
//            cidade = "Rio de Janeiro",
//            uf = "RJ"
//        ),
//        servicos = listOf(
//            Service(
//                idServico = 1,
//                nomeServico = "Corte de Cabelo",
//                duracaoServico = LocalTime.of(0, 30),
//                descricaoServico = "Corte masculino tradicional",
//                precoServico = "R$ 30,00",
//                proficional = listOf("João", "Lucas")
//            )
//        ),
//        proficionais = initProfessionals()
//    ),
//    Enterprise(
//        2,
//        nomeEmpresa = "Barbearia Beta",
//        endereco = Address(
//            cep = "01310-100",
//            logradouro = "Avenida Paulista",
//            numero = "1000",
//            cidade = "São Paulo",
//            uf = "SP"
//        ),
//        servicos = listOf(
//            Service(
//                idServico = 2,
//                nomeServico = "Barba Completa",
//                duracaoServico = LocalTime.of(0, 20),
//                descricaoServico = "Barba bem feita e alinhada",
//                precoServico = "R$ 25,00",
//                proficional = listOf("Pedro", "Marcos")
//            )
//        ),
//        proficionais = initProfessionals()
//    )
//)

fun initProfessionals() = listOf(
    "João",
    "Pedro",
    "Lucas",
    "Marcos",
    "José"
)


fun haversine(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val R = 6371 // Raio da Terra em quilômetros
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c
}


