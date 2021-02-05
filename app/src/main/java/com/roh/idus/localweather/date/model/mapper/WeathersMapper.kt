package com.roh.idus.localweather.date.model.mapper

import com.roh.idus.localweather.date.network.Weathers
import javax.inject.Inject

class WeathersMapper @Inject constructor(
        private val weatherMapper: WeatherMapper
) {
    fun transform(weathers: Weathers): com.roh.idus.localweather.domain.Weathers =
            with(weathers) {
                com.roh.idus.localweather.domain.Weathers(
                        locationTitle = locationTitle,
                        locationId = locationId,
                        weathers = weatherList.map { weatherMapper.transform(it) }
                )
            }
}