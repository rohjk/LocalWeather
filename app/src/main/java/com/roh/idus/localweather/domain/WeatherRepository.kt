package com.roh.idus.localweather.domain

import io.reactivex.Single

interface WeatherRepository {
    fun getWeathersBySearch(search: String): Single<List<WeatherInfo>>
}