package ru.fedorenko.weatherforecast.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.fedorenko.weatherforecast.core.util.Resource
import ru.fedorenko.weatherforecast.domain.model.WeatherModel

interface WeatherRepository {

    ///////////////////////////////////////////////////////////////////////////
    // GET
    ///////////////////////////////////////////////////////////////////////////

    fun getWeather(latitude: Double, longitude: Double): Flow<Resource<WeatherModel>>
}