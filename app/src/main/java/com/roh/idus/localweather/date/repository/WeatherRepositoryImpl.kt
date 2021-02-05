package com.roh.idus.localweather.date.repository

import com.roh.idus.localweather.date.datasource.WeatherDataSource
import com.roh.idus.localweather.date.model.Location
import com.roh.idus.localweather.date.model.mapper.WeatherInfoMapper
import com.roh.idus.localweather.date.network.WeatherResponse
import com.roh.idus.localweather.domain.WeatherRepository
import com.roh.idus.localweather.domain.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
        private val weatherDataSource: WeatherDataSource,
        private val weatherInfoMapper: WeatherInfoMapper
) : WeatherRepository {

    override fun getWeathersBySearch(search: String): Single<List<WeatherInfo>> {
        return getLocations(search).flatMapIterable { it }.flatMap { location ->
            getWeathers(location.id)
        }.flatMap {
            Observable.just(weatherInfoMapper.transform(it))
        }.toList()
    }

    private fun getLocations(search: String): Observable<List<Location>> {
        return weatherDataSource.getLocations(search)
    }

    private fun getWeathers(id: Long): Observable<WeatherResponse> {
        return weatherDataSource.getWeathers(id)
    }


}