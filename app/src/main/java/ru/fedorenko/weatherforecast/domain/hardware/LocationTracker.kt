package ru.fedorenko.weatherforecast.domain.hardware

import android.location.Location
import ru.fedorenko.weatherforecast.core.util.Resource

interface LocationTracker {
    suspend fun getCurrentLocation(): Resource<Location>
}