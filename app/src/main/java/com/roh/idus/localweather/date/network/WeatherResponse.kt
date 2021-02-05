package com.roh.idus.localweather.date.network

import com.google.gson.annotations.SerializedName
import com.roh.idus.localweather.date.model.Weather

data class WeatherResponse (
    @SerializedName("title")
    val locationTitle: String,
    @SerializedName("woeid")
    val locationId: Long,
    @SerializedName("consolidated_weather")
    val weatherList: List<Weather>
)