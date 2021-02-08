package com.roh.idus.localweather.data.repository

import com.roh.idus.localweather.data.datasource.WeatherDataSource
import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.model.mapper.WeatherInfoMapper
import com.roh.idus.localweather.data.model.WeatherInfoDTO
import com.roh.idus.localweather.data.model.mapper.LocationMapper
import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.repository.WeatherRepository
import com.roh.idus.localweather.domain.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
        private val weatherDataSource: WeatherDataSource,
        private val weatherInfoMapper: WeatherInfoMapper,
        private val locationMapper: LocationMapper
) : WeatherRepository {

    override fun getLocation(search: String): Observable<List<Location>> {
        return weatherDataSource.getLocations(search).map {
            it.map { locationMapper.transform(it) }
        }
    }

    override fun getWeatherInfo(id: Long): Observable<WeatherInfo> {
        return weatherDataSource.getWeatherInfo(id).map {
            weatherInfoMapper.transform(it)
        }
    }
}