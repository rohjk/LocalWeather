package com.roh.idus.localweather.date.model.mapper

import com.roh.idus.localweather.date.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {
    fun transform(weather: Weather): com.roh.idus.localweather.domain.Weather =
            with(weather) {
                return com.roh.idus.localweather.domain.Weather(
                        id = id,
                        humidity = humidity,
                        icon = icon,
                        state = state,
                        temp = temp
                )
            }
}