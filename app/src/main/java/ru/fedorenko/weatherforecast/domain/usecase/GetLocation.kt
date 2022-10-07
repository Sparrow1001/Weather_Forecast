package ru.fedorenko.weatherforecast.domain.usecase

import android.location.Location
import ru.fedorenko.weatherforecast.core.util.Resource
import ru.fedorenko.weatherforecast.domain.hardware.LocationTracker

class GetLocation(
    private val location: LocationTracker,
) {

    suspend operator fun invoke(): Resource<Location> {
        return location.getCurrentLocation()
    }
}