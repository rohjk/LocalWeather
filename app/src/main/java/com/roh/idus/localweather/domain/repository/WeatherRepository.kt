package com.roh.idus.localweather.domain.repository

import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single

interface WeatherRepository {
    fun getLocation(search: String): Observable<List<Location>>
    fun getWeatherInfo(id: Long): Observable<WeatherInfo>
}