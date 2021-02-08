package com.roh.idus.localweather.data.datasource

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.model.WeatherInfoDTO
import io.reactivex.Observable

interface WeatherDataSource {
    fun getLocations(search: String): Observable<List<LocationDTO>>
    fun getWeatherInfo(id: Long): Observable<WeatherInfoDTO>
}