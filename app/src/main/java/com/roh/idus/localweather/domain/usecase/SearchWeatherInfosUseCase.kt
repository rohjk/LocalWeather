package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.LocationWeather
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class SearchWeatherInfosUseCase @Inject constructor(
        private val searchLocationsUseCase: SearchLocationsUseCase,
        private val getLocationWeatherUseCase: GetLocationWeatherUseCase
) {
    operator fun invoke(search: String): Single<List<LocationWeather>> {
        return searchLocationsUseCase(search).flatMapIterable { it }.concatMapEager { location ->
            getLocationWeatherUseCase(location.id)
        }.flatMap {
            Observable.just(it)
        }.toList()
    }
}