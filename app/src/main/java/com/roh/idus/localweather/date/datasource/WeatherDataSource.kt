package com.roh.idus.localweather.date.datasource

import com.roh.idus.localweather.date.model.Location
import com.roh.idus.localweather.date.network.Weathers
import io.reactivex.Observable

interface WeatherDataSource {
    fun getLocations(search: String): Observable<List<Location>>
    fun getWeathers(id: Long): Observable<Weathers>
}