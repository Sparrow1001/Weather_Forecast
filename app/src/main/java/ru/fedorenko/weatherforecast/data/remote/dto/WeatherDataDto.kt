package ru.fedorenko.weatherforecast.data.remote.dto

import com.squareup.moshi.Json

data class WeatherDataDto(
    @field:Json(name = "time") val time: List<String>,
    @field:Json(name = "temperature_2m") val temperatures: List<Double>,
    @field:Json(name = "weathercode") val weatherCodes: List<Double>,
    @field:Json(name = "pressure_msl") val pressures: List<Double>,
    @field:Json(name = "windspeed_10m") val windSpeeds: List<Double>,
    @field:Json(name = "relativehumidity_2m") val humidity: List<Double>,
)
