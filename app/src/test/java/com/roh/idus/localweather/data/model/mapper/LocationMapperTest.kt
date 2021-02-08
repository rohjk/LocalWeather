package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.LocationDTO
import com.roh.idus.localweather.domain.model.Location
import com.roh.idus.localweather.domain.model.LocationWeather
import org.junit.Test

import org.junit.Assert.*

class LocationMapperTest {

    val id = 1234L
    val title = "testLocation"
    val locationType = "testLocationType"
    val lattLong = "testLattLong"

    val locationMapper = LocationMapper()

    @Test
    fun transform() {
        val expected = Location (
                id = id,
                title = title,
                locationType = locationType,
                lattLong = lattLong
        )

        val inputLocationDTO = LocationDTO (
                id = id,
                title = title,
                locationType = locationType,
                lattLong = lattLong
        )

        val actual = locationMapper.transform(inputLocationDTO)

        assertEquals(expected, actual)
    }
}