package com.roh.idus.localweather.data.datasource

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.network.WeatherServiceApi
import com.roh.idus.localweather.data.model.LocationWeatherDTO
import com.roh.idus.localweather.di.IOScheduler
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
                } ?: Observable.error(Throwable("EMPTY_LOCATIONS"))
            } else {
                Observable.error(Throwable("FAILURE_TO_GET_LOCATIONS_${response.code()}"))
            }
        }
    }

    override fun getWeatherInfo(id: Long): Observable<LocationWeatherDTO> {
       return weatherServiceApi.getWeather(id).subscribeOn(scheduler).flatMap { response ->
           if (response.isSuccessful) {
               response.body()?.let {
                   Observable.just(it)
               } ?: Observable.error(Throwable("EMPTY_WEATHERS"))
           } else {
               Observable.error(Throwable("FAILURE_TO_GET_WEATHER_INFO_${response.code()}"))
           }
       }
    }

}