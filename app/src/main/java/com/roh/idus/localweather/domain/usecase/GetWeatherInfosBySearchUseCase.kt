package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.WeatherInfo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetWeatherInfosBySearchUseCase @Inject constructor(
        private val getLocationBySearchUseCase: GetLocationBySearchUseCase,
        private val getWeatherInfoUseCase: GetWeatherInfoUseCase
) {
    operator fun invoke(search: String): Single<List<WeatherInfo>> {
        return getLocationBySearchUseCase(search).flatMapIterable { it }.concatMapEager { location ->
            getWeatherInfoUseCase(location.id)
        }.flatMap {
            Observable.just(it)
        }.toList()
    }
}