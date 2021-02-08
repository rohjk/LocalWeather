package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.domain.model.Location
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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