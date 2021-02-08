package com.roh.idus.localweather.domain.model

data class Location (
    val id: Long,
    val title: String,
    val locationType: String,
    val lattLong: String,
)