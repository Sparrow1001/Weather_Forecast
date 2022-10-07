package ru.fedorenko.weatherforecast.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.fedorenko.weatherforecast.core.util.Resource
import ru.fedorenko.weatherforecast.domain.model.WeatherModel
import ru.fedorenko.weatherforecast.domain.repository.WeatherRepository

class GetWeather(
    private val repository: WeatherRepository,
) {

    operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<WeatherModel>> {
        return repository.getWeather(latitude = latitude, longitude = longitude)
    }
}