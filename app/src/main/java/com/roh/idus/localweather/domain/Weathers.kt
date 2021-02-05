package com.roh.idus.localweather.domain

data class Weathers (
    val locationTitle: String,
    val locationId: Long,
    val weathers: List<Weather>
)