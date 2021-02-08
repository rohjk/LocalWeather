package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.WeatherDTO
import com.roh.idus.localweather.domain.model.Weather
import com.roh.idus.localweather.utils.IconUrlGenerator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherMapper @Inject constructor(
        private val iconUrlGenerator: IconUrlGenerator
) {
    fun transform(weather: WeatherDTO): Weather =
            with(weather) {
                return Weather(
                    id = id,
                    humidity = humidity,
                    icon = iconUrlGenerator.getPngIconUrl(icon),
                    state = state,
                    temp = temp
                )
            }

}