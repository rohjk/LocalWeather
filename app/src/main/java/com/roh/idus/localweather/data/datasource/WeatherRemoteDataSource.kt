package com.roh.idus.localweather.data.datasource

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.network.WeatherServiceApi
import com.roh.idus.localweather.data.model.LocationWeatherDTO
import com.roh.idus.localweather.di.IOScheduler
import com.roh.idus.localweather.error.HttpRequestFailException
import com.roh.idus.localweather.error.NullResponseBodyException
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(
        private val weatherServiceApi: WeatherServiceApi,
        @IOScheduler private val scheduler: Scheduler
) : WeatherDataSource {

    override fun getLocations(search: String): Observable<List<LocationDTO>> {
        return weatherServiceApi.getLocations(search).subscribeOn(scheduler).flatMap { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    Observable.just(it)
                } ?: Observable.error(NullResponseBodyException("get location response body is null"))
            } else {
                Observable.error(HttpRequestFailException("failure to get locations :${response.code()}"))
            }
        }
    }

    override fun getLocationWeather(id: Long): Observable<LocationWeatherDTO> {
       return weatherServiceApi.getWeather(id).subscribeOn(scheduler).flatMap { response ->
           if (response.isSuccessful) {
               response.body()?.let {
                   Observable.just(it)
               } ?: Observable.error(Throwable(NullResponseBodyException("get location weather response body is null")))
           } else {
               Observable.error(HttpRequestFailException("failure to get location weather :${response.code()}"))
           }
       }
    }

}