package com.roh.idus.localweather.localweather.adapter

import com.roh.idus.localweather.domain.model.Weather
import com.roh.idus.localweather.domain.model.WeatherInfo

class WeatherInfoViewModel constructor(
    private val weaterInfo: WeatherInfo
) {

    private val weathers = weaterInfo.weathers

    private val defaultWeather = Weather (
            id = 0,
            icon = "",
            state = "-",
            humidity = 0,
            temp = 0.0
    )

    val localTitle = weaterInfo.locationTitle
    val todayWeather = weathers.getOrNull(0) ?: defaultWeather
    val tomorowWeather = weathers.getOrNull(1) ?: defaultWeather

}