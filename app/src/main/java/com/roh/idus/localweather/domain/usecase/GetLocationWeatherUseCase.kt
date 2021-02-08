package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetLocationWeatherUseCase @Inject constructor(
        private val weatherRepository: WeatherRepository,
) {
    operator fun invoke(id: Long): Observable<LocationWeather> = weatherRepository.getLocationWeather(id)
}