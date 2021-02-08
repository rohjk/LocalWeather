package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.BuildConfig
import com.roh.idus.localweather.data.model.WeatherDTO
import com.roh.idus.localweather.domain.model.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {
    fun transform(weather: WeatherDTO): Weather =
            with(weather) {
                return Weather(
                    id = id,
                    humidity = humidity,
                    icon = getIconUri(icon),
                    state = state,
                    temp = temp
                )
            }

    private fun getIconUri(iconType: String) = BuildConfig.BASE_HOST + "static/img/weather/png/${iconType}.png"
}