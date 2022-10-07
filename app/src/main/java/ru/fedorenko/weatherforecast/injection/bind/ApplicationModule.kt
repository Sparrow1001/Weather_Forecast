package ru.fedorenko.weatherforecast.injection.bind

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.fedorenko.weatherforecast.data.hardware.AndroidLocationTracker
import ru.fedorenko.weatherforecast.data.repository.WeatherRepositoryImp
import ru.fedorenko.weatherforecast.domain.hardware.LocationTracker
import ru.fedorenko.weatherforecast.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindConverterFactory(moshiConverterFactory: MoshiConverterFactory): Converter.Factory

    @Binds
    @Singleton
    abstract fun bindWeatherRepository(weatherRepositoryImp: WeatherRepositoryImp): WeatherRepository

    @Binds
    @Singleton
    abstract fun bindLocationTracker(androidLocationTracker: AndroidLocationTracker): LocationTracker
}