package com.roh.idus.localweather.date.model.mapper

import com.roh.idus.localweather.date.network.WeatherResponse
import com.roh.idus.localweather.domain.WeatherInfo
import javax.inject.Inject

class WeatherInfoMapper @Inject constructor(
        private val weatherMapper: WeatherMapper
) {
    fun transform(weatherResponse: WeatherResponse): WeatherInfo =
            with(weatherResponse) {
                WeatherInfo(
                        locationTitle = locationTitle,
                        locationId = locationId,
                        weathers = weatherList.map { weatherMapper.transform(it) }
                )
            }
}