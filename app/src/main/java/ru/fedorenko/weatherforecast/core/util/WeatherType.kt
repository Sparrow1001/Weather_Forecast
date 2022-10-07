package ru.fedorenko.weatherforecast.core.util

import androidx.annotation.DrawableRes
import ru.fedorenko.weatherforecast.R

sealed class WeatherType(
    val name: UniversalText,
    @DrawableRes val icon: Int,
) {

    companion object {
        fun fromWeatherCode(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }

    object ClearSky : WeatherType(
        name = UniversalText.Resource(id = R.string.clear_sky),
        icon = R.drawable.ic_sunny
    )

    object MainlyClear : WeatherType(
        name = UniversalText.Resource(id = R.string.mainly_clear),
        icon = R.drawable.ic_cloudy
    )

    object PartlyCloudy : WeatherType(
        name = UniversalText.Resource(id = R.string.partly_cloudy),
        icon = R.drawable.ic_cloudy
    )

    object Overcast : WeatherType(
        name = UniversalText.Resource(id = R.string.overcast),
        icon = R.drawable.ic_cloudy
    )

    object Foggy : WeatherType(
        name = UniversalText.Resource(id = R.string.foggy),
        icon = R.drawable.ic_very_cloudy
    )

    object DepositingRimeFog : WeatherType(
        name = UniversalText.Resource(id = R.string.depositing_rime_fog),
        icon = R.drawable.ic_very_cloudy
    )

    object LightDrizzle : WeatherType(
        name = UniversalText.Resource(id = R.string.light_drizzle),
        icon = R.drawable.ic_rainshower
    )

    object ModerateDrizzle : WeatherType(
        name = UniversalText.Resource(id = R.string.moderate_drizzle),
        icon = R.drawable.ic_rainshower
    )

    object DenseDrizzle : WeatherType(
        name = UniversalText.Resource(id = R.string.dense_drizzle),
        icon = R.drawable.ic_rainshower
    )

    object LightFreezingDrizzle : WeatherType(
        name = UniversalText.Resource(id = R.string.light_freezing_drizzle),
        icon = R.drawable.ic_snowyrainy
    )

    object DenseFreezingDrizzle : WeatherType(
        name = UniversalText.Resource(id = R.string.dense_freezing_drizzle),
        icon = R.drawable.ic_snowyrainy
    )

    object SlightRain : WeatherType(
        name = UniversalText.Resource(id = R.string.slight_rain),
        icon = R.drawable.ic_rainy
    )

    object ModerateRain : WeatherType(
        name = UniversalText.Resource(id = R.string.moderate_rain),
        icon = R.drawable.ic_rainy
    )

    object HeavyRain : WeatherType(
        name = UniversalText.Resource(id = R.string.heavy_rain),
        icon = R.drawable.ic_rainy
    )

    object HeavyFreezingRain : WeatherType(
        name = UniversalText.Resource(id = R.string.heavy_freezing_rain),
        icon = R.drawable.ic_snowyrainy
    )

    object SlightSnowFall : WeatherType(
        name = UniversalText.Resource(id = R.string.slight_snow_fall),
        icon = R.drawable.ic_snowy
    )

    object ModerateSnowFall : WeatherType(
        name = UniversalText.Resource(id = R.string.moderate_snow_fall),
        icon = R.drawable.ic_heavysnow
    )

    object HeavySnowFall : WeatherType(
        name = UniversalText.Resource(id = R.string.heavy_snow_fall),
        icon = R.drawable.ic_heavysnow
    )

    object SnowGrains : WeatherType(
        name = UniversalText.Resource(id = R.string.snow_grains),
        icon = R.drawable.ic_heavysnow
    )

    object SlightRainShowers : WeatherType(
        name = UniversalText.Resource(id = R.string.slight_rain_showers),
        icon = R.drawable.ic_rainshower
    )

    object ModerateRainShowers : WeatherType(
        name = UniversalText.Resource(id = R.string.moderate_rain_showers),
        icon = R.drawable.ic_rainshower
    )

    object ViolentRainShowers : WeatherType(
        name = UniversalText.Resource(id = R.string.violent_rain_showers),
        icon = R.drawable.ic_rainshower
    )

    object SlightSnowShowers : WeatherType(
        name = UniversalText.Resource(id = R.string.slight_snow_showers),
        icon = R.drawable.ic_snowy
    )

    object HeavySnowShowers : WeatherType(
        name = UniversalText.Resource(id = R.string.heavy_snow_showers),
        icon = R.drawable.ic_snowy
    )

    object ModerateThunderstorm : WeatherType(
        name = UniversalText.Resource(id = R.string.moderate_thunderstorm),
        icon = R.drawable.ic_thunder
    )

    object SlightHailThunderstorm : WeatherType(
        name = UniversalText.Resource(id = R.string.slight_hail_thunderstorm),
        icon = R.drawable.ic_rainythunder
    )

    object HeavyHailThunderstorm : WeatherType(
        name = UniversalText.Resource(id = R.string.heavy_hail_thunderstorm),
        icon = R.drawable.ic_rainythunder
    )
}