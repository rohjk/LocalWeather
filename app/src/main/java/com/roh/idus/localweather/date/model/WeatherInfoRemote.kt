package com.roh.idus.localweather.date.model

import com.google.gson.annotations.SerializedName
import com.roh.idus.localweather.date.model.WeatherRemote

data class WeatherInfoRemote (
    @SerializedName("title")
    val locationTitle: String,
    @SerializedName("woeid")
    val locationId: Long,
    @SerializedName("consolidated_weather")
    val weatherList: List<WeatherRemote>
)