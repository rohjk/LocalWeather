package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetWeatherInfoUseCase @Inject constructor(
        private val weatherRepository: WeatherRepository,
) {
    operator fun invoke(id: Long): Observable<LocationWeather> = weatherRepository.getWeatherInfo(id)
}