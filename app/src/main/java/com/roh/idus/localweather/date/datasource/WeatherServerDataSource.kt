package com.roh.idus.localweather.date.datasource

import com.roh.idus.localweather.date.model.Location
import com.roh.idus.localweather.date.network.WeatherServiceApi
import com.roh.idus.localweather.date.network.Weathers
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherServerDataSource @Inject constructor(
        private val weatherServiceApi: WeatherServiceApi
) : WeatherDataSource {

    override fun getLocations(search: String): Observable<List<Location>> {
        return weatherServiceApi.getLocations(search).subscribeOn(Schedulers.io()).flatMap { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    Observable.just(it)
                } ?: Observable.error(Throwable("EMPTY_LOCATIONS"))
            } else {
                Observable.error(Throwable("FAILURE_TO_GET_LOCATIONS_${response.code()}"))
            }
        }
    }

    override fun getWeathers(id: Long): Observable<Weathers> {
       return weatherServiceApi.getWeather(id).subscribeOn(Schedulers.io()).flatMap { response ->
           if (response.isSuccessful) {
               response.body()?.let {
                   Observable.just(it)
               } ?: Observable.error(Throwable("EMPTY_WEATHERS"))
           } else {
               Observable.error(Throwable("FAILURE_TO_GET_LOCATIONS_${response.code()}"))
           }
       }
    }

}