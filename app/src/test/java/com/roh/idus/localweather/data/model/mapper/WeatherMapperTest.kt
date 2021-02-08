package com.roh.idus.localweather.data.model.mapper

import com.roh.idus.localweather.data.model.WeatherDTO
import com.roh.idus.localweather.domain.model.Weather
import com.roh.idus.localweather.utils.IconUrlGenerator
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class WeatherMapperTest {

    lateinit var weatherMapper: WeatherMapper

    @MockK
    lateinit var iconUrlGenerator: IconUrlGenerator

    val id = 1234L
    val humidity = 100
    val state = "testState"
    val temp = 11.1
    val iconType = "testType"
    val iconUrl = "testUrl"

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        weatherMapper = WeatherMapper(iconUrlGenerator)

        every { iconUrlGenerator.getPngIconUrl(iconType) } returns iconUrl
    }

    @Test
    fun transform() {
        val expected = Weather(
                id = id,
                state = state,
                temp = temp,
                humidity = humidity,
                icon = iconUrl
        )

        val input = WeatherDTO(
                id = id,
                state = state,
                temp = temp,
                humidity = humidity,
                icon = iconType
        )

        val actual = weatherMapper.transform(input)

        assertEquals(expected, actual)

        verify(exactly = 1) {
            iconUrlGenerator.getPngIconUrl(iconType)
        }
    }
}