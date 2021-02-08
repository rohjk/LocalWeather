package com.roh.idus.localweather.data.repository

import com.roh.idus.localweather.data.datasource.WeatherDataSource
import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.model.mapper.WeatherInfoMapper
import com.roh.idus.localweather.data.model.WeatherInfoDTO
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

    private fun getLocations(search: String): Observable<List<LocationDTO>> {
        return weatherDataSource.getLocations(search)
    }

    private fun getWeathers(id: Long): Observable<WeatherInfoDTO> {
        return weatherDataSource.getWeatherInfo(id)
    }


}