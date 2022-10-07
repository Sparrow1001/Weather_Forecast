package ru.fedorenko.weatherforecast.domain.model

data class WeatherModel(
    val weatherDataPerDay: List<List<WeatherDataModel>>, // 0-6 indexed for future days, second list contains 0-23 indexed hours for the day
    val currentWeatherData: WeatherDataModel?,
)