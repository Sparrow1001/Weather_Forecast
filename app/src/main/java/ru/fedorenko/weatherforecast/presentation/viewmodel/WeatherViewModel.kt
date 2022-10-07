package ru.fedorenko.weatherforecast.presentation.viewmodel

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.fedorenko.weatherforecast.core.util.Resource
import ru.fedorenko.weatherforecast.core.util.UniversalText
import ru.fedorenko.weatherforecast.domain.model.WeatherModel
import ru.fedorenko.weatherforecast.domain.usecase.GetLocation
import ru.fedorenko.weatherforecast.domain.usecase.GetWeather
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeather: GetWeather,
    private val getLocation: GetLocation,
) : ViewModel() {

    var weather: WeatherModel? by mutableStateOf(null)
        private set

    var loading: Boolean by mutableStateOf(false)
        private set

    var error: UniversalText by mutableStateOf(UniversalText.Empty)
        private set

    private var job: Job? = null

    fun onGetWeather() {
        job?.cancel()
        job = viewModelScope.launch {
            loading = true
            error = UniversalText.Empty
            getCurrentLocation()
        }
    }

    private suspend fun getCurrentLocation() {
        when (val result = getLocation()) {
            is Resource.Success -> {
                observeWeather(result.data!!)
            }
            is Resource.Error -> {
                loading = false
                error = result.message!!
            }
            else -> Unit
        }
    }

    private suspend fun observeWeather(location: Location) {
        getWeather(latitude = location.latitude, longitude = location.longitude).collectLatest { result ->
            when (result) {
                is Resource.Success -> {
                    loading = false
                    weather = result.data!!
                }
                is Resource.Error -> {
                    loading = false
                    error = result.message!!
                }
                is Resource.Loading -> {
                    loading = true
                }
            }
        }
    }
}