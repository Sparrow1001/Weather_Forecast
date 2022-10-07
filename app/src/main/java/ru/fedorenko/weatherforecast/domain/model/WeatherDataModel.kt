package ru.fedorenko.weatherforecast.domain.model

import ru.fedorenko.weatherforecast.core.util.WeatherType
import java.time.LocalDateTime

data class WeatherDataModel(
    val time: LocalDateTime,
    val type: WeatherType,
    val temperature: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
)