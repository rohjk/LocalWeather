package com.roh.idus.localweather.domain.usecase

import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.repository.WeatherRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchLocationsUseCase @Inject constructor(
        private val weatherRepository: WeatherRepository,
) {
    operator fun invoke(search: String): Observable<List<Location>> = weatherRepository.getLocation(search)
}