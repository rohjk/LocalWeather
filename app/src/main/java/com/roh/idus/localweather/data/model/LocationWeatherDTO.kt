package com.roh.idus.localweather.data.model

import com.google.gson.annotations.SerializedName

data class LocationWeatherDTO (
    @SerializedName("title")
    val locationTitle: String,
    @SerializedName("woeid")
    val locationId: Long,
    @SerializedName("consolidated_weather")
    val weatherList: List<WeatherDTO>
)