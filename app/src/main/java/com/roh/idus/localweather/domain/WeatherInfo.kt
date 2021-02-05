package com.roh.idus.localweather.domain

data class WeatherInfo (
    val locationTitle: String,
    val locationId: Long,
    val weathers: List<Weather>
)