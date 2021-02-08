package com.roh.idus.localweather.date.datasource

import com.roh.idus.localweather.date.model.LocationRemote
import com.roh.idus.localweather.date.model.WeatherInfoRemote
import io.reactivex.Observable

interface WeatherDataSource {
    fun getLocations(search: String): Observable<List<LocationRemote>>
    fun getWeatherInfo(id: Long): Observable<WeatherInfoRemote>
}