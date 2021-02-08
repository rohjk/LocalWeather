package com.roh.idus.localweather.date.model.mapper

import com.roh.idus.localweather.date.model.WeatherInfoRemote
import com.roh.idus.localweather.domain.model.WeatherInfo
import javax.inject.Inject

class WeatherInfoMapper @Inject constructor(
        private val weatherMapper: WeatherMapper
) {
    fun transform(weatherInfoRemote: WeatherInfoRemote): WeatherInfo =
            with(weatherInfoRemote) {
                WeatherInfo(
                    locationTitle = locationTitle,
                    locationId = locationId,
                    weathers = weatherList.map { weatherMapper.transform(it) }
                )
            }
}