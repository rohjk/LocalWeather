package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.LocationWeatherDTO
import com.roh.idus.localweather.domain.model.LocationWeather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationWeatherMapper @Inject constructor(
        private val weatherMapper: WeatherMapper
) {
    fun transform(locationWeatherDTO: LocationWeatherDTO): LocationWeather =
            with(locationWeatherDTO) {
                LocationWeather(
                    locationTitle = locationTitle,
                    locationId = locationId,
                    weathers = weatherList.map { weatherMapper.transform(it) }
                )
            }
}