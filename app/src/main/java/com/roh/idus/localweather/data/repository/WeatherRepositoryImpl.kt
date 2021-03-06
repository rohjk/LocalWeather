package com.roh.idus.localweather.data.repository

import com.roh.idus.localweather.data.datasource.WeatherDataSource
import com.roh.idus.localweather.data.model.mapper.LocationWeatherMapper
import com.roh.idus.localweather.data.model.mapper.LocationMapper
import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.repository.WeatherRepository
import com.roh.idus.localweather.domain.model.LocationWeather
import io.reactivex.Observable
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
        private val weatherDataSource: WeatherDataSource,
        private val locationWeatherMapper: LocationWeatherMapper,
        private val locationMapper: LocationMapper
) : WeatherRepository {

    override fun getLocations(search: String): Observable<List<Location>> {
        return weatherDataSource.getLocations(search).map {
            it.map { locationMapper.transform(it) }
        }
    }

    override fun getLocationWeather(id: Long): Observable<LocationWeather> {
        return weatherDataSource.getLocationWeather(id).map {
            locationWeatherMapper.transform(it)
        }
    }
}