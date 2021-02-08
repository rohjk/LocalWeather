package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.WeatherInfoDTO
import com.roh.idus.localweather.domain.model.WeatherInfo
import javax.inject.Inject

class WeatherInfoMapper @Inject constructor(
        private val weatherMapper: WeatherMapper
) {
    fun transform(weatherInfoDTO: WeatherInfoDTO): WeatherInfo =
            with(weatherInfoDTO) {
                WeatherInfo(
                    locationTitle = locationTitle,
                    locationId = locationId,
                    weathers = weatherList.map { weatherMapper.transform(it) }
                )
            }
}