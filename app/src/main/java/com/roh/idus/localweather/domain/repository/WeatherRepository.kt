package com.roh.idus.localweather.domain.repository

import com.roh.idus.localweather.domain.model.WeatherInfo
import io.reactivex.Single

interface WeatherRepository {
    fun getWeather(search: String): Single<List<WeatherInfo>>
}