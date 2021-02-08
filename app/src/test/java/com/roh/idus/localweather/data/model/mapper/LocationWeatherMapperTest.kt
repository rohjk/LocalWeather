package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.LocationWeatherDTO
import com.roh.idus.localweather.data.model.WeatherDTO
import com.roh.idus.localweather.domain.model.LocationWeather
import com.roh.idus.localweather.domain.model.Weather
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class LocationWeatherMapperTest {

    lateinit var locationWeatherMapper: LocationWeatherMapper

    @MockK
    lateinit var weatherMapper: WeatherMapper

    @MockK
    lateinit var weatherDTO: WeatherDTO
    @MockK
    lateinit var weather: Weather

    val locationTitle = "testLocationTitle"
    val locationId = 1234L

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        locationWeatherMapper = LocationWeatherMapper(weatherMapper)

        every { weatherMapper.transform(weatherDTO) } returns weather
    }

    @Test
    fun transform() {
        val expected = LocationWeather (
                locationTitle,
                locationId,
                listOf(weather)
        )

        val input = LocationWeatherDTO (
                locationTitle,
                locationId,
                listOf(weatherDTO)
        )

        val actual = locationWeatherMapper.transform(input)

        assertEquals(expected, actual)

        verify(exactly = 1) {
            weatherMapper.transform(weatherDTO)
        }
    }
}