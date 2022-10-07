package ru.fedorenko.weatherforecast.presentation.screen

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import ru.fedorenko.weatherforecast.core.util.UniversalText
import ru.fedorenko.weatherforecast.presentation.component.WeatherCard
import ru.fedorenko.weatherforecast.presentation.component.WeatherForecast
import ru.fedorenko.weatherforecast.presentation.viewmodel.WeatherViewModel

private val PERMISSIONS = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

@Composable
fun WeatherScreen(
    viewmodel: WeatherViewModel = hiltViewModel(),
) {
    val permissions = rememberMultiplePermissionsState(permissions = PERMISSIONS)
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) permissions.launchMultiplePermissionRequest()
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    LaunchedEffect(key1 = permissions.allPermissionsGranted) {
        if (permissions.allPermissionsGranted) viewmodel.onGetWeather()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            viewmodel.weather?.currentWeatherData?.let { WeatherCard(weather = it) }
            Spacer(modifier = Modifier.height(16.dp))
            viewmodel.weather?.weatherDataPerDay?.getOrNull(0)?.let { WeatherForecast(data = it) }
        }
        if (viewmodel.loading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        if (viewmodel.error != UniversalText.Empty) {
            Text(
                text = viewmodel.error.asString(),
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}