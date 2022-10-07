package ru.fedorenko.weatherforecast.data.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.fedorenko.weatherforecast.data.remote.dto.WeatherDto

interface WeatherApi {

    ///////////////////////////////////////////////////////////////////////////
    // GET
    ///////////////////////////////////////////////////////////////////////////

    @GET("/v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
    ): WeatherDto
}