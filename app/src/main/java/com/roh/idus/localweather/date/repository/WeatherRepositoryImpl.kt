package com.roh.idus.localweather.date.repository

import com.roh.idus.localweather.date.datasource.WeatherDataSource
import com.roh.idus.localweather.date.model.Location
import com.roh.idus.localweather.date.model.mapper.WeathersMapper
import com.roh.idus.localweather.domain.WeatherRepository
import com.roh.idus.localweather.domain.Weathers
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
        private val weatherDataSource: WeatherDataSource,
        private val weathersMapper: WeathersMapper
) : WeatherRepository {

    override fun getWeathersBySearch(search: String): Single<List<Weathers>> {
        return getLocations(search).flatMapIterable { it }.flatMap { location ->
            getWeathers(location.id)
        }.flatMap {
            Observable.just(weathersMapper.transform(it))
        }.toList()
    }

    private fun getLocations(search: String): Observable<List<Location>> {
        return weatherDataSource.getLocations(search)
    }

    private fun getWeathers(id: Long): Observable<com.roh.idus.localweather.date.network.Weathers> {
        return weatherDataSource.getWeathers(id)
    }


}