package ru.fedorenko.weatherforecast.data.mapper

import ru.fedorenko.weatherforecast.core.util.WeatherType
import ru.fedorenko.weatherforecast.data.remote.dto.WeatherDataDto
import ru.fedorenko.weatherforecast.data.remote.dto.WeatherDto
import ru.fedorenko.weatherforecast.domain.model.WeatherDataModel
import ru.fedorenko.weatherforecast.domain.model.WeatherModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.sortedWithDaysAndHours(): List<List<WeatherDataModel>> {
    return time.mapIndexed { index, time ->
        IndexedWeatherData(
            index = index,
            data = WeatherDataModel(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                type = WeatherType.fromWeatherCode(weatherCodes[index].toInt()),
                temperature = temperatures[index],
                pressure = pressures[index],
                windSpeed = windSpeeds[index],
                humidity = humidity[index]
            )
        )
    }.groupBy { data ->
        data.index / 24
    }.mapValues { entry ->
        entry.value.map { it.data }
    }.values.toList()
}

fun WeatherDto.toWeatherModel(): WeatherModel {
    val weatherData = data.sortedWithDaysAndHours()
    val now = LocalDateTime.now()

    // get current time weather data by hour
    val currentWeatherData =
        if (now.hour == 23 && now.minute > 29) {
            weatherData.getOrNull(1)?.getOrNull(0)
        } else {
            weatherData.getOrNull(0)?.getOrNull(if (now.minute < 30) now.hour else now.hour + 1)
        }
    return WeatherModel(
        weatherDataPerDay = weatherData,
        currentWeatherData = currentWeatherData
    )
}

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherDataModel,
)
