package ru.fedorenko.weatherforecast.injection.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import ru.fedorenko.weatherforecast.BuildConfig
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var tree: Timber.DebugTree

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(tree)
    }
}