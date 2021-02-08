package com.roh.idus.localweather.domain.repository

import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.model.LocationWeather
import io.reactivex.Observable

interface WeatherRepository {
    fun getLocation(search: String): Observable<List<Location>>
    fun getLocationWeather(id: Long): Observable<LocationWeather>
}