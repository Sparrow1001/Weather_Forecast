package ru.fedorenko.weatherforecast.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.fedorenko.weatherforecast.core.util.Resource
import ru.fedorenko.weatherforecast.data.mapper.toWeatherModel
import ru.fedorenko.weatherforecast.data.remote.datasource.WeatherDataSource
import ru.fedorenko.weatherforecast.domain.model.WeatherModel
import ru.fedorenko.weatherforecast.domain.repository.WeatherRepository

class WeatherRepositoryImp(
    private val datasource: WeatherDataSource,
) : WeatherRepository {

    ///////////////////////////////////////////////////////////////////////////
    // GET
    ///////////////////////////////////////////////////////////////////////////

    override fun getWeather(latitude: Double, longitude: Double): Flow<Resource<WeatherModel>> {
        return datasource.getWeather(latitude = latitude, longitude = longitude).map { resource ->
            return@map when (resource) {
                is Resource.Success -> Resource.Success(resource.data!!.toWeatherModel())
                is Resource.Error -> Resource.Error(resource.message!!)
                is Resource.Loading -> Resource.Loading()
            }
        }
    }
}