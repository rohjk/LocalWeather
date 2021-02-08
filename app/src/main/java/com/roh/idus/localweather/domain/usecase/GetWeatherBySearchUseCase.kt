package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.WeatherInfo
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherBySearchUseCase @Inject constructor(
        private val weatherRepository: WeatherRepository,
) {
    fun execute(search: String): Single<List<WeatherInfo>> = weatherRepository.getWeather(search)
}