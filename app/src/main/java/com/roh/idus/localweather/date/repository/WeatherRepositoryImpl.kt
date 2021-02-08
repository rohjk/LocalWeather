package com.roh.idus.localweather.date.repository

import com.roh.idus.localweather.date.datasource.WeatherDataSource
import com.roh.idus.localweather.date.model.LocationRemote
import com.roh.idus.localweather.date.model.mapper.WeatherInfoMapper
import com.roh.idus.localweather.date.model.WeatherInfoRemote
import com.roh.idus.localweather.domain.repository.WeatherRepository
import com.roh.idus.localweather.domain.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
        private val weatherDataSource: WeatherDataSource,
        private val weatherInfoMapper: WeatherInfoMapper
) : WeatherRepository {

    override fun getWeather(search: String): Single<List<WeatherInfo>> {
        return getLocations(search).flatMapIterable { it }.concatMapEager { location ->
            getWeathers(location.id)
        }.flatMap {
            Observable.just(weatherInfoMapper.transform(it))
        }.toList()
    }

    private fun getLocations(search: String): Observable<List<LocationRemote>> {
        return weatherDataSource.getLocations(search)
    }

    private fun getWeathers(id: Long): Observable<WeatherInfoRemote> {
        return weatherDataSource.getWeatherInfo(id)
    }


}