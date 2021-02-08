package com.roh.idus.localweather.domain.model

data class WeatherInfo (
    val locationTitle: String,
    val locationId: Long,
    val weathers: List<Weather>
)