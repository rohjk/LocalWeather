package com.roh.idus.localweather.localweather.adapter

import com.roh.idus.localweather.domain.model.WeatherInfo

class WeatherInfoViewModel constructor(
    private val weaterInfo: WeatherInfo
) {

    private val weathers = weaterInfo.weathers

    val localTitle = weaterInfo.locationTitle
    val todayWeather = weathers[0]
    val tomorowWeather = weathers[1]

}