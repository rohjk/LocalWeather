package com.roh.idus.localweather.date.model.mapper

import com.roh.idus.localweather.BuildConfig
import com.roh.idus.localweather.date.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {
    fun transform(weather: Weather): com.roh.idus.localweather.domain.model.Weather =
            with(weather) {
                return com.roh.idus.localweather.domain.model.Weather(
                    id = id,
                    humidity = humidity,
                    icon = getIconUri(icon),
                    state = state,
                    temp = temp
                )
            }

    private fun getIconUri(iconType: String) = BuildConfig.BASE_HOST + "static/img/weather/png/64/${iconType}.png"
}