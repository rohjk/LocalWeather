package com.roh.idus.localweather.domain.model

data class LocationWeather (
    val locationTitle: String,
    val locationId: Long,
    val weathers: List<Weather>
)