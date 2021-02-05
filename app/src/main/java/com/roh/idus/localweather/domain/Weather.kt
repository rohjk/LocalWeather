package com.roh.idus.localweather.domain

data class Weather (
    val id: Long,
    val state: String,
    val icon: String,
    val temp: String,
    val humidity: String,
)