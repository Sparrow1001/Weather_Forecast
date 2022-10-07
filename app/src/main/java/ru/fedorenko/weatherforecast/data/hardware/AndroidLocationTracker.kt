package ru.fedorenko.weatherforecast.data.hardware

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import ru.fedorenko.weatherforecast.R
import ru.fedorenko.weatherforecast.core.util.Resource
import ru.fedorenko.weatherforecast.core.util.UniversalText
import ru.fedorenko.weatherforecast.domain.hardware.LocationTracker
import kotlin.coroutines.resume

@SuppressLint("MissingPermission")
class AndroidLocationTracker(
    private val app: Application,
    private val client: FusedLocationProviderClient,
) : LocationTracker {

    override suspend fun getCurrentLocation(): Resource<Location> {
        return withContext(Dispatchers.Default) {
            return@withContext when {
                !hasAccessFineLocationPermission() -> Resource.Error(UniversalText.Resource(id = R.string.error_access_fine_location))
                !hasAccessCoarseLocationPermission() -> Resource.Error(UniversalText.Resource(id = R.string.error_access_coarse_location))
                !enabledGps() -> Resource.Error(UniversalText.Resource(id = R.string.error_gps_not_enabled))
                else -> suspendCancellableCoroutine { continuation ->
                    with(client.lastLocation) {
                        if (isComplete) continuation.resume(
                            if (isSuccessful) Resource.Success(result) else Resource.Error(UniversalText.Resource(id = R.string.error_unknown))
                        )
                        addOnSuccessListener {
                            continuation.resume(
                                if (it == null) Resource.Error(UniversalText.Resource(R.string.error_unknown)) else Resource.Success(it)
                            )
                        }
                        addOnFailureListener { continuation.resume(Resource.Error(UniversalText.Resource(R.string.error_unknown))) }
                        addOnCanceledListener { continuation.cancel() }
                    }
                }
            }
        }
    }

    private fun hasAccessFineLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(app, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasAccessCoarseLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(app, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun enabledGps(): Boolean {
        val manager = app.getSystemService(LocationManager::class.java)
        return manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || manager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }
}