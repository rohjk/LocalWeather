package com.roh.idus.localweather.domain.model

import com.roh.idus.localweather.domain.model.Weather

data class WeatherInfo (
    val locationTitle: String,
    val locationId: Long,
    val weathers: List<Weather>
)