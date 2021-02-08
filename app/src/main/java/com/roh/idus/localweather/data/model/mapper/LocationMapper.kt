package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.data.model.WeatherInfoDTO
import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.model.WeatherInfo
import javax.inject.Inject

class LocationMapper @Inject constructor() {
    fun transform(locationDTO: LocationDTO): Location =
            with(locationDTO) {
                Location(
                        id = id,
                        title = title,
                        locationType = locationType,
                        lattLong = lattLong
                )
            }
}