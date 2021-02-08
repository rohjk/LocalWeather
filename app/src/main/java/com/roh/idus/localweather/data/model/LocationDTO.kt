package com.roh.idus.localweather.data.model

import com.google.gson.annotations.SerializedName

data class LocationDTO (
    @SerializedName("woeid")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("latt_long")
    val lattLong: String,
)